package com.example.demo.controller.admin;

import com.example.demo.model.MauXe;
import com.example.demo.service.ServiceMauXe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Controller
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/admin/mau-xe")
public class ControllerMauXe {
    @Autowired
    ServiceMauXe serviceMauXe;

    @GetMapping("/index")
    public List<MauXe> index() {
        List<MauXe> list = serviceMauXe.getAll();
        return list;
    }

    @PostMapping("/create")
    public String create(@RequestBody MauXe mauXe) {
        mauXe.setNgayTao(LocalDateTime.now());
        serviceMauXe.insert(mauXe);
        return "Saved...";
    }

    @PutMapping("/update/{id}")
    public MauXe update(@PathVariable("id") UUID id, @RequestBody MauXe mauXe) {
        MauXe cp = serviceMauXe.detail(id).get();
        cp.setTen(mauXe.getTen());
        cp.setHopSo(mauXe.getHopSo());
        cp.setHangXe(mauXe.getHangXe());
        cp.setDungTichXiLanh(mauXe.getDungTichXiLanh());
        cp.setNamSanXuat(mauXe.getNamSanXuat());
        cp.setKichThuocTongThe(mauXe.getKichThuocTongThe());
        cp.setMauSac(mauXe.getMauSac());
        cp.setNgayTao(mauXe.getNgayTao());
        cp.setNgaySua(LocalDateTime.now());
        serviceMauXe.insert(cp);
        return serviceMauXe.detail(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        serviceMauXe.delete(id);
        return "Deleted...";
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MauXe> getById(@PathVariable UUID id) {
        Optional<MauXe> nhaCungCap = serviceMauXe.detail(id);
        if (nhaCungCap.isPresent()) {
            return new ResponseEntity<>(nhaCungCap.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
