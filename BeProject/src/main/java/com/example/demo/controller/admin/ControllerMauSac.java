package com.example.demo.controller.admin;

import com.example.demo.model.MauSac;
import com.example.demo.service.ServiceMauSac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/admin/mau-sac")
public class ControllerMauSac {
    @Autowired
    ServiceMauSac serviceMauSac;

    @GetMapping("/index")
    public List<MauSac> index() {
        List<MauSac> list = serviceMauSac.getAll();
        return list;
    }

    @PostMapping("/create")
    public String create(@RequestBody MauSac mauSac) {
        mauSac.setNgayTao(LocalDateTime.now());
        serviceMauSac.insert(mauSac);
        return "Saved...";
    }

    @PutMapping("/update/{id}")
    public MauSac update(@PathVariable("id") Integer id, @RequestBody MauSac mauSac) {
        MauSac cp = serviceMauSac.detail(id).get();
        cp.setTen(mauSac.getTen());
        cp.setNgayTao(mauSac.getNgayTao());
        cp.setNgaySua(mauSac.getNgaySua());
        serviceMauSac.insert(cp);
        return serviceMauSac.detail(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        serviceMauSac.delete(id);
        return "Deleted...";
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MauSac> getById(@PathVariable Integer id) {
        Optional<MauSac> mauSac = serviceMauSac.detail(id);
        if (mauSac.isPresent()) {
            return new ResponseEntity<>(mauSac.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
