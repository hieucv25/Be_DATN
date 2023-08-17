package com.example.demo.service;

import com.example.demo.auth.AuthenticationRequest;
import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khachHangRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        NhanVien nv = nhanVienRepository.findByEmail(authenticationRequest.getEmail()).orElse(null);
        if (nv == null) {
            KhachHang kh = khachHangRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
            var jwtToken = jwtService.generateToken(kh, authorities);
            var jwtRefreshToken = jwtService.generateRefreshToken(kh);
            return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(nv.getCv().getTenChucVu()));
        var jwtToken = jwtService.generateToken(nv, authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(nv);
        return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }
}
