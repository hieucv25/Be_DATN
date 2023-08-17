package com.example.demo.controller.admin;

import com.example.demo.model.ChiTietPhuTung;
import com.example.demo.service.ServiceChiTietPhuTung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials = "true")
@RestController
@Controller
@RequestMapping("/api/admin/chi-tiet-phu-tung")
public class ControllerChiTietPhuKien {
    @Autowired
    private ServiceChiTietPhuTung serviceChiTietPhuTung;

    @GetMapping("/index")
    public List<ChiTietPhuTung> index() {
        List<ChiTietPhuTung> list = serviceChiTietPhuTung.getAll();
        return list;
    }

    @PostMapping("/create")
    public String create(@RequestBody ChiTietPhuTung chiTietPhuTung) {
        chiTietPhuTung.setNgayTao(LocalDateTime.now());
        serviceChiTietPhuTung.insert(chiTietPhuTung);
        return "Saved...";
    }

    @PutMapping("/update/{id}")
    public ChiTietPhuTung update(@PathVariable("id") UUID id, @RequestBody ChiTietPhuTung chiTietPhuTung) {
        ChiTietPhuTung cp = serviceChiTietPhuTung.detail(id).get();
        cp.setSoLuong(chiTietPhuTung.getSoLuong());
        cp.setNgayTao(chiTietPhuTung.getNgayTao());
        cp.setPhuKien(chiTietPhuTung.getPhuKien());
        cp.setMauXe(chiTietPhuTung.getMauXe());
        cp.setNgaySua(LocalDateTime.now());
        serviceChiTietPhuTung.insert(cp);
        return serviceChiTietPhuTung.detail(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        serviceChiTietPhuTung.delete(id);
        return "Deleted...";
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ChiTietPhuTung> getById(@PathVariable UUID id) {
        Optional<ChiTietPhuTung> ct = serviceChiTietPhuTung.detail(id);
        if (ct.isPresent()) {
            return new ResponseEntity<>(ct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
