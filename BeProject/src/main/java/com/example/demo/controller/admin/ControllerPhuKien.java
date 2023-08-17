package com.example.demo.controller.admin;

import com.example.demo.model.PhuKien;
import com.example.demo.service.ServicePhuKien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin/phu-kien")
public class ControllerPhuKien {

    private String maPhuKienCuoiCung = "";

    @Autowired
    ServicePhuKien servicePhuKien;

    @GetMapping("/index")
    public List<PhuKien> index(Model model) {
        List<PhuKien> list = servicePhuKien.getAll();
        return list;
    }

    @GetMapping("/Page/{number}")
    public Page<PhuKien> Page(@PathVariable("number") int number) {

        Pageable pageable = PageRequest.of(number, 2);
        Page<PhuKien> page = servicePhuKien.phanTrang(pageable);
        return page;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PhuKien phuKien) {
        if (phuKien.getTenPhuKien() == null || phuKien.getTenPhuKien().isEmpty()) {
            return ResponseEntity.badRequest().body("Tên Không Được để trống");
        }
        if (phuKien.getGia() == null) {
            return ResponseEntity.badRequest().body("Giá Không Được để trống");
        }

        String maPhuKien = generateUniqueMaPhuKien();
        phuKien.setMaPhuKien(maPhuKien);

        phuKien.setNgayTao(LocalDateTime.now());
        servicePhuKien.insert(phuKien);
        maPhuKienCuoiCung = maPhuKien; // Lưu trữ mã phụ kiện mới tạo

        return ResponseEntity.ok("Phụ Kiện đã được thành công");
    }

    private String generateUniqueMaPhuKien() {
        String maPhuKien = generateMaPhuKien(); // Tạo mã phụ kiện
        List<PhuKien> existingPhuKienList = servicePhuKien.getAll(); // Lấy danh sách mã phụ kiện hiện có

        while (isMaPhuKienExists(maPhuKien, existingPhuKienList)) {
            int soLuong = Integer.parseInt(maPhuKien.substring(2)); // Lấy số lượng từ mã phụ kiện
            soLuong++;
            maPhuKien = String.format("PK%02d", soLuong); // Tạo mã phụ kiện mới
        }

        return maPhuKien;
    }

    private boolean isMaPhuKienExists(String maPhuKien, List<PhuKien> existingPhuKienList) {
        for (PhuKien existingPhuKien : existingPhuKienList) {
            if (existingPhuKien.getMaPhuKien().equals(maPhuKien)) {
                return true; // Mã phụ kiện đã tồn tại
            }
        }
        return false; // Mã phụ kiện không tồn tại trong danh sách hiện có
    }

    private String generateMaPhuKien() {
        String maPhuKien = "";
        if (!maPhuKienCuoiCung.isEmpty()) {
            int soLuong = Integer.parseInt(maPhuKienCuoiCung.substring(2)); // Lấy số lượng từ mã phụ kiện cuối cùng đã tạo
            soLuong++;
            maPhuKien = String.format("PK%02d", soLuong);
        } else {
            maPhuKien = "PK01"; // Mã phụ kiện đầu tiên
        }
        return maPhuKien;
    }

    @PutMapping("/update/{id}")
    public PhuKien update(@PathVariable("id") UUID id, @RequestBody PhuKien phuKien) {
        PhuKien pk = servicePhuKien.detail(id).get();
        pk.setMaPhuKien(phuKien.getMaPhuKien());
        pk.setTenPhuKien(phuKien.getTenPhuKien());
        pk.setGia(phuKien.getGia());
        pk.setSoLuongTon(phuKien.getSoLuongTon());
        pk.setNgayTao(phuKien.getNgayTao());
        pk.setNgaySua(LocalDateTime.now());
        pk.setImage(phuKien.getImage());
        pk.setTrangThai(phuKien.getTrangThai());
        servicePhuKien.insert(pk);
        return servicePhuKien.detail(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        servicePhuKien.delete(id);
        return "redirect:/phukien/index";
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PhuKien> getById(@PathVariable("id") UUID id) {
        Optional<PhuKien> phuKien = servicePhuKien.detail(id);
        if (phuKien.isPresent()) {
            return new ResponseEntity<>(phuKien.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{keyword}")
    public List<PhuKien> search(@PathVariable("keyword") String keyword) {
        List<PhuKien> nhanViens = servicePhuKien.timkiempk(keyword);
        return nhanViens;
    }

    @GetMapping("/searchncc/{nhaCungCap}")
    public List<PhuKien> searchncc(@PathVariable("nhaCungCap") Integer nhaCungCap) {
        List<PhuKien> nhanViens = servicePhuKien.findbyncc(nhaCungCap);
        return nhanViens;
    }

    @GetMapping("/searchtt/{trangThai}")
    public List<PhuKien> searchtt(@PathVariable("trangThai") Integer trangThai) {
        List<PhuKien> nhanViens = servicePhuKien.findbytt(trangThai);
        return nhanViens;
    }

    @GetMapping("/searchgia/{gia}")
    public List<PhuKien> searchgia(@PathVariable("gia") Double gia) {
        List<PhuKien> nhanViens = servicePhuKien.findbygia(gia);
        return nhanViens;
    }
}
