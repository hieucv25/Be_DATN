package com.example.demo.controller.admin;

import com.example.demo.model.LichSuLichHen;
import com.example.demo.service.Impl.SericeLichSuLichHenImpl;
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
@RequestMapping("/api/admin/lich-su-lich-hen")
public class ControllerLichSuLichHen {
    @Autowired
    SericeLichSuLichHenImpl svls;

    @GetMapping("/index")
    private List<LichSuLichHen> index() {
        return svls.getAll();
    }

    @PostMapping("/save")
    private LichSuLichHen save(@RequestBody LichSuLichHen ls) {
        ls.setNgayTao(LocalDateTime.now());
        svls.save(ls);
        return svls.getById(ls.getId());
    }

    @DeleteMapping("/delete/{id}")
    private boolean delete(@PathVariable("id") String id) {
        svls.deleteById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
        if (svls.existById(UUID.fromString(id.toUpperCase(Locale.ROOT)))) {
            return false;
        }
        return true;
    }

    @PutMapping("/update/{id}")
    private LichSuLichHen update(@PathVariable("id") String id,@RequestBody LichSuLichHen ls){
        LichSuLichHen lsUpdate = svls.getById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
        lsUpdate.setNgaySua(LocalDateTime.now());
        svls.update(lsUpdate);
        return svls.getById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
    }
}
