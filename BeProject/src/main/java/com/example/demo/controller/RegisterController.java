package com.example.demo.controller;

import com.example.demo.model.KhachHang;
import com.example.demo.service.ServiceKhachHang;
import com.example.demo.service.ServiceNhanVien;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/register")
@RestController
public class RegisterController {
    @Autowired
    ServiceKhachHang svkh;
    @Autowired
    ServiceNhanVien svnv;

    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/validation")
    private ResponseEntity<String> validation(@RequestBody KhachHang kh) {
        return ResponseEntity.ok(svkh.validation(kh));
    }

    @PostMapping("/save_user")
    private ResponseEntity<String> saveUser(@RequestBody KhachHang kh) {
        List<KhachHang> listSort = svkh.sort(Sort.by("NgayTao").descending());
        List<KhachHang> listAll = svkh.getAll();
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKhachHang(svkh.generateIDCustomer(kh,listSort,listAll));
        khachHang.setNgayTao(LocalDateTime.now());
        khachHang.setHoTen(kh.getHoTen());
        khachHang.setEmail(kh.getEmail());
        khachHang.setSdt(kh.getSdt());
        khachHang.setMatKhauMaHoa(kh.getMatKhau());
        khachHang.setMatKhauMaHoa(passwordEncoder.encode(kh.getMatKhau()));
        return ResponseEntity.ok("success");
    }
}
