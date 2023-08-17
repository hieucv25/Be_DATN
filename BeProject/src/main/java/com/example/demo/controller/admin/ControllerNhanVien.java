package com.example.demo.controller.admin;

import com.example.demo.model.NhanVien;
import com.example.demo.service.ServiceNhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@Controller
@RequestMapping("/api/admin/nhan-vien")
public class ControllerNhanVien {

    private String maNhanVienCuoiCung = "";

    @Autowired
    ServiceNhanVien serviceNhanVien;

    @GetMapping("/index")
    public List<NhanVien> index(Model model) {
        List<NhanVien> list = serviceNhanVien.getAll();
        return list;
    }

    @GetMapping("/Page/{number}")
    public Page<NhanVien> Page(@PathVariable("number") int number) {

        Pageable pageable = PageRequest.of(number, 2);
        Page<NhanVien> page = serviceNhanVien.phanTrang(pageable);
        return page;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody NhanVien nhanVien) {
        String expression = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        String regex = "^(0\\d{9,10})$";

        if (nhanVien.getHoTen() == null) {
            return ResponseEntity.badRequest().body("Không được để trống");
        }
        if (nhanVien.getTinhThanh() == null || nhanVien.getQuanHuyen().isEmpty() || nhanVien.getXaPhuong().isEmpty()) {
            return ResponseEntity.badRequest().body("Địa Chỉ Không được trống");
        }
        if (nhanVien.getEmail() == null || nhanVien.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Email không được để trống");
        } else {
            NhanVien nhanvienemail = serviceNhanVien.findbyemail(nhanVien.getEmail());
            if (nhanvienemail != null) {
                return ResponseEntity.badRequest().body("Email đã tồn tại");
            }
        }
        if (nhanVien.getSdt() == null || nhanVien.getSdt().isEmpty()) {
            return ResponseEntity.badRequest().body("Số Điện Thoại Không được để trống");
        } else {
            NhanVien nhanviensdt = serviceNhanVien.findbysdt(nhanVien.getSdt());
            if (nhanviensdt != null) {
                return ResponseEntity.badRequest().body("Sdt đã tồn tại");
            }

        }
        if (nhanVien.getCccd() == null || nhanVien.getCccd().isEmpty()) {
            return ResponseEntity.badRequest().body("CMND không được để trống");
        } else {
            NhanVien nhanviencmnd = serviceNhanVien.findbycmnd(nhanVien.getCccd());
            if (nhanviencmnd != null) {
                return ResponseEntity.badRequest().body("Cmnd đã tồn tại");
            }
        }
        if (nhanVien.getCccd().length() < 12 || nhanVien.getCccd().length() > 12) {
            return ResponseEntity.badRequest().body("Cmnd không ít hơn 15 và quá 15 ký tự");
        }
        if (nhanVien.getNgaySinh() == null) {
            return ResponseEntity.badRequest().body("Ngày Sình Không được để trống");
        }
        if (nhanVien.getEmail().length() > 20) {
            return ResponseEntity.badRequest().body("Email Không quá 50 ký tự");
        }
        if (!nhanVien.getEmail().matches(expression)) {
            return ResponseEntity.badRequest().body("Email Không đúng đinh dạng");
        }
        if (nhanVien.getSdt().length() > 11 || nhanVien.getSdt().length() < 11) {
            return ResponseEntity.badRequest().body("Sđt Không ít hơn 11, quá 11 ký tự và bắt đầu từ số 0");
        }
        if (!nhanVien.getSdt().matches(regex)) {
            return ResponseEntity.badRequest().body("Sdt bắt đầu bằng số 0");
        }
        if (nhanVien.getCv() == null) {
            return ResponseEntity.badRequest().body("Chúc vụ không được bỏ trống");
        }
        if (nhanVien.getHoTen() == null || nhanVien.getHoTen().isEmpty()) {
            return ResponseEntity.badRequest().body("Tên Không được để trống");
        }
        String maNhanVien = generateUniqueMaNhanVien();
        nhanVien.setMaNhanVien(maNhanVien);

        nhanVien.setNgayTao(LocalDateTime.now());
        serviceNhanVien.insert(nhanVien);
        maNhanVienCuoiCung = maNhanVien;
        return ResponseEntity.ok("Nhân viên đã được tạo thành công.");
    }

    private String generateUniqueMaNhanVien() {
        String maNhanVien = generateMaNhanVien(); // Tạo mã phụ kiện
        List<NhanVien> existingNhanVienList = serviceNhanVien.getAllNV(); // Lấy danh sách mã phụ kiện hiện có

        while (isMaNhanVienExists(maNhanVien, existingNhanVienList)) {
            int soLuong = Integer.parseInt(maNhanVien.substring(2)); // Lấy số lượng từ mã phụ kiện
            soLuong++;
            maNhanVien = String.format("NV%02d", soLuong); // Tạo mã phụ kiện mới
        }

        return maNhanVien;
    }

    private boolean isMaNhanVienExists(String maNhanVien, List<NhanVien> existingNhanVienList) {
        for (NhanVien existingNhanVien : existingNhanVienList) {
            if (existingNhanVien.getMaNhanVien().equals(maNhanVien)) {
                return true; // Mã phụ kiện đã tồn tại
            }
        }
        return false; // Mã phụ kiện không tồn tại trong danh sách hiện có
    }

    private String generateMaNhanVien() {
        String maNhanVien = "";
        if (!maNhanVienCuoiCung.isEmpty()) {
            int soLuong = Integer.parseInt(maNhanVienCuoiCung.substring(2)); // Lấy số lượng từ mã phụ kiện cuối cùng đã
            // tạo
            soLuong++;
            maNhanVien = String.format("NV%02d", soLuong);
        } else {
            maNhanVien = "NV01"; // Mã phụ kiện đầu tiên
        }
        return maNhanVien;
    }

    @PutMapping("/update/{id}")
    public NhanVien update(@PathVariable("id") UUID id,
                           @RequestBody NhanVien nhanVien) {
        NhanVien nv = serviceNhanVien.detail(id).get();
        nv.setMaNhanVien(nhanVien.getMaNhanVien());
        nv.setHoTen(nhanVien.getHoTen());
        nv.setTinhThanh(nhanVien.getTinhThanh());
        nv.setQuanHuyen(nhanVien.getQuanHuyen());
        nv.setXaPhuong(nhanVien.getXaPhuong());
        nv.setSoNha(nhanVien.getSoNha());
        nv.setEmail(nhanVien.getEmail());
        nv.setSdt(nhanVien.getSdt());
        nv.setNgaySua(LocalDateTime.now());
        nv.setNgayTao(nhanVien.getNgayTao());
        nv.setNgaySinh(nhanVien.getNgaySinh());
        nv.setMatKhau(nhanVien.getMatKhau());
        nv.setTrangThai(nhanVien.getTrangThai());
        nv.setCv(nhanVien.getCv());
        nv.setAnh(nhanVien.getAnh());
        serviceNhanVien.insert(nv);
        return serviceNhanVien.detail(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        serviceNhanVien.delete(id);
        return "redirect:/nhanvien/index";
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<NhanVien> getById(@PathVariable("id") UUID id) {
        Optional<NhanVien> nhanVien = serviceNhanVien.detail(id);
        if (nhanVien.isPresent()) {
            return new ResponseEntity<>(nhanVien.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{keyword}")
    public List<NhanVien> search(@PathVariable("keyword") String keyword) {
        List<NhanVien> nhanViens = serviceNhanVien.timkiemnv(keyword);
        return nhanViens;
    }

    @GetMapping("/searchns/{startDate}/{endDate}")
    public List<NhanVien> searchns(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Không hợp lệ");
        }
        List<NhanVien> nhanViens = serviceNhanVien.khoangns(startDate, endDate);
        return nhanViens;
    }

    @GetMapping("/searchchucvu/{chucVu}")
    public List<NhanVien> searchchucvu(@PathVariable("chucVu") Integer chucVu) {
        List<NhanVien> nhanViens = serviceNhanVien.findbychucvu(chucVu);
        return nhanViens;
    }

    @GetMapping("/searchtt/{trangThai}")
    public List<NhanVien> searchtt(@PathVariable("trangThai") Integer trangThai) {
        List<NhanVien> nhanViens = serviceNhanVien.findbytt(trangThai);
        return nhanViens;
    }
}
