package com.example.demo.controller.staff;

import com.example.demo.model.NhanVien;
import com.example.demo.service.ServiceNhanVien;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    @Autowired
    ServiceNhanVien svnv;

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/getStaff")
    public ResponseEntity<List<NhanVien>> view() {
        List<NhanVien> list = svnv.getAll();
        return ResponseEntity.ok(list);
    }
}
