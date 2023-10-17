package com.example.demo.service;

import com.example.demo.auth.AuthenticationRequest;
import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.model.Author_KhachHang;
import com.example.demo.model.Author_NhanVien;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.Author_KhachHang_Repository;
import com.example.demo.repository.Author_NhanVien_Repository;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khachHangRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Author_NhanVien_Repository auth_NhanVien;
    private final Author_KhachHang_Repository auth_khachHang;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        NhanVien nv = nhanVienRepository.findByEmail(authenticationRequest.getEmail()).orElse(null);
        if (nv == null) {
            KhachHang kh = khachHangRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
            var jwtToken = jwtService.generateToken(kh, authorities);
            var jwtRefreshToken = jwtService.generateRefreshToken(kh);
            Author_KhachHang auth = new Author_KhachHang();
            auth.setRefreshToken(jwtRefreshToken);
            auth.setKhachHang(kh);
            LocalDateTime now = LocalDateTime.now();
            auth.setExpirationTime(now.plusMinutes(60));
            auth_khachHang.saveAndFlush(auth);
            return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(nv.getCv().getTenChucVu()));
        var jwtToken = jwtService.generateToken(nv, authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(nv);
        Author_NhanVien auth = new Author_NhanVien();
        auth.setRefreshToken(jwtRefreshToken);
        auth.setNhanVien(nv);
        auth.setChucVu(nv.getCv());
        LocalDateTime now = LocalDateTime.now();
        auth.setExpirationTime(now.plusMinutes(60));
        auth_NhanVien.saveAndFlush(auth);
        return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }
}
