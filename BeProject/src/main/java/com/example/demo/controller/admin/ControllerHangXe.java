package com.example.demo.controller.admin;

import com.example.demo.model.HangXe;
import com.example.demo.service.ServiceHangXe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin/hang-xe")
public class ControllerHangXe {
    @Autowired
    ServiceHangXe serviceHangXe;

    @GetMapping("/index")
    public List<HangXe> index() {
        List<HangXe> list = serviceHangXe.getAll();
        return list;
    }

    @PostMapping("/create")
    public String create(@RequestBody HangXe hangXe) {
        hangXe.setNgayTao(LocalDateTime.now());
        serviceHangXe.insert(hangXe);
        return "Saved...";
    }

    @PutMapping("/update/{id}")
    public HangXe update(@PathVariable("id") Integer id, @RequestBody HangXe hangXe) {
        HangXe cp = serviceHangXe.detail(id).get();
        cp.setTen(hangXe.getTen());
        cp.setQuocGia(hangXe.getQuocGia());
        cp.setNgayTao(hangXe.getNgayTao());
        cp.setNgaySua(LocalDateTime.now());
        serviceHangXe.insert(cp);
        return serviceHangXe.detail(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        serviceHangXe.delete(id);
        return "Deleted...";
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<HangXe> getById(@PathVariable Integer id) {
        Optional<HangXe> mauSac = serviceHangXe.detail(id);
        if (mauSac.isPresent()) {
            return new ResponseEntity<>(mauSac.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
