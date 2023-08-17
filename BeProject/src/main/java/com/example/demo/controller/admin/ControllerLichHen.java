package com.example.demo.controller.admin;

import com.example.demo.model.LichHen;
import com.example.demo.model.LichSuLichHen;
import com.example.demo.service.Impl.ServiceLichHenChiTietImpl;
import com.example.demo.service.Impl.ServiceLichHenImpl;
import com.example.demo.service.Impl.SericeLichSuLichHenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/api/admin/lich-hen")
public class ControllerLichHen {
    @Autowired
    ServiceLichHenImpl svlh;
    @Autowired
    ServiceLichHenChiTietImpl lhctsv;
    @Autowired
    SericeLichSuLichHenImpl lslhsv;

    @GetMapping("/index/{number}")
    private Page<LichHen> index(@PathVariable("number") int number) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
        return svlh.paging(pageable);
    }

    @GetMapping("/maxPage")
    private int totalPage() {
        int total = svlh.getAll().size();
        int maxPage = total / 5;
        if (total % 5 == 0) {
            return maxPage;
        } else {
            return maxPage + 1;
        }
    }

    @GetMapping("/getAllAppointment")
    private List<LichHen> index() {
        return svlh.getAll();
    }

    @GetMapping("/getById/{id}")
    private LichHen getById(@PathVariable("id") String id) {
        return svlh.getById(UUID.fromString(id));
    }

    @PostMapping("/save")
    private LichHen save(@RequestBody LichHen lh) {
        List<LichHen> list = svlh.findAllAndSort(Sort.by("ngayTao").ascending());
        if (lh.getMaLichHen() == null) {
            lh.setMaLichHen("LH" + list.size());
            for (LichHen lichHen : list
            ) {
                if (lichHen.getMaLichHen().equalsIgnoreCase(lh.getMaLichHen())) {
                    int index = Integer.valueOf(lh.getMaLichHen().substring(2));
                    int next = index + 1;
                    lh.setMaLichHen("LH" + next);
                }
            }
            for (LichHen lichHen : list
            ) {
                if (lichHen.getMaLichHen().equalsIgnoreCase(lh.getMaLichHen())) {
                    int index = Integer.valueOf(lh.getMaLichHen().substring(2));
                    int next = index + 1;
                    lh.setMaLichHen("LH" + next);
                }
            }
        }
        lh.setNgayTao(LocalDateTime.now());
        System.out.println(lh.isLoaiLichHen());
        svlh.save(lh);
        LichSuLichHen lslh = new LichSuLichHen();
        lslh.setTrangThai(lh.getTrangThai());
        lslh.setNgayTao(LocalDateTime.now());
        lslh.setLh(svlh.existsByMa(lh.getMaLichHen()));
        lslhsv.save(lslh);
        return svlh.existsByMa(lh.getMaLichHen());
    }

    @PutMapping("/update/{id}")
    private LichHen update(@RequestBody LichHen lh, @PathVariable("id") String id) {
        LichHen lichHen = svlh.getByIdOp(UUID.fromString(id.toUpperCase(Locale.ROOT)));
        lichHen.setLoaiLichHen(lh.isLoaiLichHen());
        lichHen.setThoiGianDat(lh.getThoiGianDat());
        lichHen.setThoiGianDuKien(lh.getThoiGianDuKien());
        lichHen.setTrangThai(lh.getTrangThai());
        lichHen.setNgaySua(LocalDateTime.now());
        lichHen.setSdt(lh.getSdt());
        System.out.println(lichHen.getSdt());
        svlh.update(lichHen);
        return lichHen;
    }

    @DeleteMapping("/delete/{id}")
    private boolean delete(@PathVariable("id") String id) {
        LichHen lh = svlh.getById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
        lslhsv.deleteByLh(lh.getId());
        lhctsv.deleteByLh(lh.getId());
        svlh.deleteById(UUID.fromString(id.toUpperCase(Locale.ROOT)));
        if (svlh.existById(UUID.fromString(id))) {
            return false;
        }
        return true;
    }

    @GetMapping("/filter/{status}/{type}/{number}")
    private Page<LichHen> filterByStatusAndType(@PathVariable(value = "status", required = false) String status,
                                                @PathVariable(value = "type", required = false) String type,
                                                @PathVariable(value = "number", required = false) int number) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
        return svlh.findByStatusAndType(Integer.valueOf(status), Boolean.valueOf(type), pageable);
    }

    @GetMapping("/filter/status/{status}/{number}")
    private Page<LichHen> filterByStatus(@PathVariable(value = "status", required = false) String status,
                                         @PathVariable(value = "number", required = false) int number) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
        return svlh.findByStatus(Integer.valueOf(status), pageable);
    }

    @GetMapping("/filter/type/{type}/{number}")
    private Page<LichHen> filterByType(@PathVariable(value = "type", required = false) String type,
                                       @PathVariable(value = "number", required = false) int number) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
        return svlh.findByType(Boolean.valueOf(type), pageable);
    }

    @GetMapping("/filter/name/{keyword}/{number}")
    private Page<LichHen> filterByName(@PathVariable(value = "keyword", required = false) String keyword,
                                       @PathVariable(value = "number", required = false) int number) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
        return svlh.findByName(keyword, pageable);
    }

    @PostMapping("/validate")
    private String validate(@RequestBody(required = false) LichHen lh){
        String regex = "^(0\\d{8,10})$";
        List<LichHen> list = svlh.getAll();
        if(lh.getThoiGianDuKien().equals("0")){
            return "Thời Gian Dự Kiến Chưa Được Chọn!!";
        }

        if(lh.getThoiGianDat().isBefore(LocalDateTime.now())){
            return "Ngày và giờ bạn đặt phải lớn hơn thời gian hiện tại!";
        }
        if(String.valueOf(lh.getKh().getId()).equals("0")){
            return"Khách Hàng Chưa Được Chọn!!";
        }
        if(lh.getSdt().isEmpty()){
            return"Chưa Nhập Số Điện Thoại!";
        }
        if(!lh.getSdt().matches(regex)){
            return"Số Điện Thoại Tối Đa 11 Kí Tự Và Phải Bắt Đầu Bằng 0!";
        }
        for (LichHen lichHen: list
        ) {
            if(lichHen.getThoiGianDat().equals(lh.getThoiGianDat())){
                return "Trùng Thời Gian Với 1 Lịch Hẹn Khác! Hãy Chọn Lại!";
            }
        }
        return "ok";
    }

    @PostMapping("/validateFormUpdate/{id}")
    private String validateFormUpdate(@PathVariable("id") String id,@RequestBody(required = false) LichHen lh){
        String regex = "^(0\\d{8,10})$";
        List<LichHen> list = svlh.getAll();
        list.remove(svlh.getById(UUID.fromString(id.toLowerCase(Locale.ROOT))));
        if(lh.getThoiGianDuKien().equals("0")){
            return "Thời Gian Dự Kiến Chưa Được Chọn!!";
        }
        if(String.valueOf(lh.getKh().getId()).equals("0")){
            return"Khách Hàng Chưa Được Chọn!!";
        }
        if(lh.getSdt().isEmpty()){
            return"Chưa Nhập Số Điện Thoại!";
        }
        if(!lh.getSdt().matches(regex)){
            return"Số Điện Thoại Tối Đa 11 Kí Tự Và Phải Bắt Đầu Bằng 0!";
        }
        for (LichHen lichHen: list
        ) {
            if(lichHen.getThoiGianDat().equals(lh.getThoiGianDat())){
                return "Trùng Thời Gian Với 1 Lịch Hẹn Khác! Hãy Chọn Lại!";
            }
        }
        return "ok";
    }
}
