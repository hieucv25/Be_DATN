package com.example.demo.controller.admin;

import com.example.demo.model.LichSuSuDungPhuKien;
import com.example.demo.service.ServiceLichSuSDPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin/lich-su-phu-kien")
public class ControllerLichSuSDPK {
    @Autowired
    ServiceLichSuSDPK serviceLichSuSDPK;

    @GetMapping("/index")
    public List<LichSuSuDungPhuKien> index() {
        List<LichSuSuDungPhuKien> list = serviceLichSuSDPK.getAll();
        return list;
    }

    @PostMapping("/insert")
    public String insert(@RequestBody LichSuSuDungPhuKien lichSuSuDungPhuKien) {
        serviceLichSuSDPK.insert(lichSuSuDungPhuKien);
        return "redirect:/lich-su-phu-kien/index";
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<LichSuSuDungPhuKien> getById(@PathVariable("id") UUID id) {
        Optional<LichSuSuDungPhuKien> lichSuSuDungPhuKien = serviceLichSuSDPK.detail(id);
        if (lichSuSuDungPhuKien.isPresent()) {
            return new ResponseEntity<>(lichSuSuDungPhuKien.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public LichSuSuDungPhuKien update(@PathVariable("id") UUID id, @RequestBody LichSuSuDungPhuKien lichSuSuDungPhuKien) {
        LichSuSuDungPhuKien ls = serviceLichSuSDPK.detail(id).get();
        ls.setSoPhuKien(lichSuSuDungPhuKien.getSoPhuKien());
        ls.setPhuKien(lichSuSuDungPhuKien.getPhuKien());
        ls.setNgayTao(lichSuSuDungPhuKien.getNgayTao());
        ls.setNgaySua(lichSuSuDungPhuKien.getNgaySua());
        serviceLichSuSDPK.insert(ls);
        return serviceLichSuSDPK.detail(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        serviceLichSuSDPK.delete(id);
        return "redirect:/lich-su-phu-kien/index";
    }
}
