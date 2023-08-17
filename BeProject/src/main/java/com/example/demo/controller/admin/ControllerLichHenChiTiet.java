package com.example.demo.controller.admin;

import com.example.demo.model.LichHenChiTiet;
import com.example.demo.service.Impl.ServiceLichHenChiTietImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/api/admin/lich-hen-chi-tiet")
public class ControllerLichHenChiTiet {
    @Autowired
    ServiceLichHenChiTietImpl svlhct;
    @GetMapping("/index")
    private List<LichHenChiTiet> index(){
        return svlhct.getAll();
    }
    @PostMapping("/save")
    private LichHenChiTiet save(@RequestBody LichHenChiTiet lhct){
        lhct.setNgayTao(LocalDateTime.now());
        svlhct.save(lhct);
        return svlhct.getById(lhct.getId());
    }
    @DeleteMapping("/delete/{id}")
    private boolean delete(@PathVariable("id")String id){
        svlhct.deleteById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
        if(svlhct.existById(UUID.fromString(id.toLowerCase(Locale.ROOT)))){
            return false;
        }
        return true;
    }
    @PutMapping("/update/{id}")
    private LichHenChiTiet update(@PathVariable("id")String id , @RequestBody LichHenChiTiet lhct){
        LichHenChiTiet lhUpdate = svlhct.getById(UUID.fromString(id.toLowerCase(Locale.ROOT)));
        lhUpdate.setNgaySua(LocalDateTime.now());
        svlhct.update(lhUpdate);
        return  svlhct.getById(lhct.getId());
    }
}
