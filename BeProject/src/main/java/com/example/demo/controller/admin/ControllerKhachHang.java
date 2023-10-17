package com.example.demo.controller.admin;

import com.example.demo.model.KhachHang;
import com.example.demo.model.LichHen;
import com.example.demo.service.Impl.ServiceKhachHangImpl;
import com.example.demo.service.Impl.ServiceLichHenChiTietImpl;
import com.example.demo.service.Impl.ServiceLichHenImpl;
import com.example.demo.service.Impl.SericeLichSuLichHenImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/api/admin/khach-hang")
public class ControllerKhachHang {
    @Autowired
    ServiceKhachHangImpl khsv;
    @Autowired
    ServiceLichHenImpl lhsv;
    @Autowired
    ServiceLichHenChiTietImpl lhctsv;
    @Autowired
    SericeLichSuLichHenImpl lslhsv;

    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/index/{number}")
    private Page<KhachHang> index(@PathVariable("number") int number) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("NgayTao").descending());
        Page<KhachHang> page = khsv.page(pageable);
        return page;
    }

    @GetMapping("/getAllCustomer")
    private List<KhachHang> index() {
        return khsv.getAll();
    }

    @GetMapping("/maxPage")
    private int totalPage() {
        int total = khsv.getAll().size();
        int maxPage = total / 5;
        if (total % 5 == 0) {
            return maxPage;
        } else {
            return maxPage + 1;
        }
    }

    @GetMapping("/getById/{id}")
    private KhachHang getById(@PathVariable("id") String id) {
        return khsv.getById(UUID.fromString(id));
    }

    @GetMapping("/getAppointmentByCustomer/{id}")
    private List<LichHen> getLhByKH(@PathVariable("id") String id) {
        System.out.println(id);
        return lhsv.getByKH(UUID.fromString(id.toUpperCase(Locale.ROOT)));
    }

    @GetMapping("/searchByName/{name}/{number}")
    private Page<KhachHang> getByName(@PathVariable("name") String name, @PathVariable("number") int number) {
        Pageable pageable = PageRequest.of(number, 5);
        Page<KhachHang> page = khsv.getByName(name.toLowerCase(), pageable);
        return page;
    }

    @PostMapping("/save")
    private KhachHang add(@RequestBody KhachHang kh) {
        List<KhachHang> list = khsv.sort(Sort.by("NgayTao").ascending());
        if (kh.getMaKhachHang() == null) {
            kh.setMaKhachHang("KH" + khsv.getAll().size());
            for (KhachHang khachHang : list
            ) {
                if (khachHang.getMaKhachHang().equalsIgnoreCase(kh.getMaKhachHang())) {
                    int index = Integer.valueOf(kh.getMaKhachHang().substring(2));
                    int next = 1;
                    kh.setMaKhachHang("KH" + (index + next));
                }
            }
            for (KhachHang khachHang : list
            ) {
                if (khachHang.getMaKhachHang().equalsIgnoreCase(kh.getMaKhachHang())) {
                    int index = Integer.valueOf(kh.getMaKhachHang().substring(2));
                    int next = 1;
                    kh.setMaKhachHang("KH" + (index + next));
                }
            }
        }
        kh.setMatKhauMaHoa(passwordEncoder.encode(kh.getMatKhau()));
        kh.setNgayTao(LocalDateTime.now());
        khsv.save(kh);
        if (khsv.existById(kh.getId())) {
            return kh;
        } else {
            return null;
        }
    }

    @PostMapping("/validate")
    private String validate(@RequestBody(required = false) KhachHang kh){
        String expression = "^[a-zA-Z0-9._%+-]{1,50}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String regexNumberPhone = "^(\\+84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-46-8])[0-9]{7}$";
        List<KhachHang> list = khsv.getAll();
        if(kh.getHoTen().isEmpty()){
            return "Họ Tên Chưa Được Nhập";
        }
        if(kh.getEmail().isEmpty()){
            return"Email Chưa Được Nhập!";
        }
        if(kh.getSdt().isEmpty()){
            return "Số Điện Thoại Chưa Được Nhập!";
        }
        for (KhachHang khachHang: list
        ) {
            if(khachHang.getSdt().equalsIgnoreCase(kh.getSdt())){
                return "Số Điện Thoại Này Đã Được Khách Hàng Khác Sử Dụng!";
            }
            if(khachHang.getEmail().equalsIgnoreCase(kh.getEmail())){
                return "Email Này Đã Được Khách Hàng Khác Sử Dụng!";
            }
        }
        if(kh.getTinhThanh().isEmpty()){
            return "Tỉnh, Thành Phố Chưa Được Chọn!";
        }
        if(kh.getQuanHuyen().isEmpty()){
            return "Quận, Huyện Chưa Được Chọn!";
        }
        if(!kh.getEmail().matches(expression)){
            return "Email Chưa Đúng Định Dạng!";
        }
        if(kh.getEmail().length()>50){
            return "Email Tối Đa 50 Kí Tự!";
        }
        if(!kh.getSdt().matches(regexNumberPhone)){
            return "Số Điện Thoại Phải Là 10 Kí Tự Số Và Phải Bắt Đầu Bằng 0!";
        }
        return "ok";
    }

    @PostMapping("/validateFormUpdate/{id}")
    private String validateFormUpdate(@RequestBody(required = false) KhachHang kh,
                                      @PathVariable("id") String id){
        String expression = "^[a-zA-Z0-9._%+-]{1,50}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String regexNumberPhone = "^(\\+84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-46-8])[0-9]{6}$";
        KhachHang kha2 = khsv.getById(UUID.fromString(id));
        List<KhachHang> list = khsv.getAll();
        System.out.println(kh.getId());
        list.remove(kha2);
        for (KhachHang kha: list
             ) {
            if(kh.getMaKhachHang().equalsIgnoreCase(kha.getMaKhachHang())){
                return "Trùng Mã Khách Hàng !";
            }
        }
        if(kh.getHoTen().isEmpty()){
            return "Họ Tên Chưa Được Nhập";
        }
        if(kh.getEmail().isEmpty()){
            return"Email Chưa Được Nhập!";
        }
        if(kh.getSdt().isEmpty()){
            return "Số Điện Thoại Chưa Được Nhập!";
        }
        for (KhachHang khachHang: list
        ) {
            if(khachHang.getSdt().equalsIgnoreCase(kh.getSdt())){
                return "Số Điện Thoại Này Đã Được Khách Hàng Khác Sử Dụng!";
            }
            if(khachHang.getEmail().equalsIgnoreCase(kh.getEmail())){
                return "Email Này Đã Được Khách Hàng Khác Sử Dụng!";
            }
        }
        if(kh.getTinhThanh().isEmpty()){
            return "Tỉnh, Thành Phố Chưa Được Chọn!";
        }
        if(kh.getQuanHuyen().isEmpty()){
            return "Quận, Huyện Chưa Được Chọn!";
        }
        if(!kh.getEmail().matches(expression)){
            return "Email Chưa Đúng Định Dạng!";
        }
        if(kh.getEmail().length()>50){
            return "Email Tối Đa 50 Kí Tự!";
        }
        if(!kh.getSdt().matches(regexNumberPhone)){
            return "Số Điện Thoại Phải Là 10 Kí Tự Số Và Phải Bắt Đầu Bằng 0!";
        }
        return "ok";
    }

    @PutMapping("/update/{id}")
    private KhachHang update(@PathVariable("id") String id, @RequestBody KhachHang kh) {
        KhachHang khUpdate = khsv.getById(UUID.fromString(id.toLowerCase(Locale.ROOT)));
        khUpdate.setMaKhachHang(kh.getMaKhachHang());
        khUpdate.setHoTen(kh.getHoTen());
        khUpdate.setEmail(kh.getEmail());
        khUpdate.setSdt(kh.getSdt());
        khUpdate.setTinhThanh(kh.getTinhThanh());
        khUpdate.setQuanHuyen(kh.getQuanHuyen());
        khUpdate.setGioiTinh(kh.isGioiTinh());
        khUpdate.setMatKhau(kh.getMatKhau());
        khUpdate.setMatKhauMaHoa(passwordEncoder.encode(kh.getMatKhau()));
        khUpdate.setNgaySua(LocalDateTime.now());
        khsv.update(khUpdate);
        return khsv.getById(UUID.fromString(id.toLowerCase(Locale.ROOT)));
    }

    @DeleteMapping("/delete/{id}")
    private boolean delete(@PathVariable("id") String id) {
        if (lhsv.getByKH(UUID.fromString(id.toUpperCase(Locale.ROOT))).size() >= 1) {
            for (LichHen lh : lhsv.getByKH(UUID.fromString(id.toUpperCase(Locale.ROOT)))
            ) {
                lslhsv.deleteByLh(lh.getId());
                lhctsv.deleteByLh(lh.getId());
            }
            lhsv.deleteByKh(UUID.fromString(id.toUpperCase(Locale.ROOT)));
            khsv.deleteById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
            if (khsv.existById(UUID.fromString(id.toLowerCase(Locale.ROOT)))) {
                return false;
            }
            return true;
        }
        khsv.deleteById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
        return true;
    }
}
