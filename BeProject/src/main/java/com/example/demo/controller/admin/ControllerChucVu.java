package com.example.demo.controller.admin;

import com.example.demo.model.ChucVu;
import com.example.demo.service.ServiceChucVu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin/chuc-vu")
public class ControllerChucVu {
    @Autowired
    ServiceChucVu serviceChucVu;

    @GetMapping("/index")
    public List<ChucVu> index(Model model) {
        List<ChucVu> list = serviceChucVu.getALl();
        return list;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ChucVu chucVu) {
        if (chucVu.getTenChucVu() == null || chucVu.getTenChucVu().isEmpty()) {
            return ResponseEntity.badRequest().body("Tên Không được để trống");
        }
        chucVu.setNgayTao(LocalDateTime.now());
        serviceChucVu.save(chucVu);
        return ResponseEntity.ok("Chức vụ đã được tạo thành công.");
    }

    @PutMapping("/update/{maChucVu}")
    public ChucVu update(@PathVariable("maChucVu") Integer maChucVu, @RequestBody ChucVu chucVu) {
        ChucVu cp = serviceChucVu.findByID(maChucVu).get();
        cp.setTenChucVu(chucVu.getTenChucVu());
        cp.setNgayTao(chucVu.getNgayTao());
        cp.setNgaySua(LocalDateTime.now());
        serviceChucVu.save(cp);
        return serviceChucVu.findByID(maChucVu).get();
    }

    @DeleteMapping("/delete/{maChucVu}")
    public String delete(@PathVariable Integer maChucVu) {
        serviceChucVu.delete(maChucVu);
        return "redirect:/chuc-vu/index";
    }

    @GetMapping("/getById/{maChucVu}")
    public ResponseEntity<ChucVu> getById(@PathVariable Integer maChucVu) {
        Optional<ChucVu> chucVu = serviceChucVu.findByID(maChucVu);
        if (chucVu.isPresent()) {
            return new ResponseEntity<>(chucVu.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}