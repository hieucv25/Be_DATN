package com.example.demo.service.Impl;

import com.example.demo.model.Author_KhachHang;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.Author_KhachHang_Repository;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.ServiceKhachHang;
import com.example.demo.service.ServiceNhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceKhachHangImpl implements ServiceKhachHang {

    @Autowired
    KhachHangRepository khrp;

    @Autowired
    Author_KhachHang_Repository auth;

    @Autowired
    ServiceNhanVien svnv;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void save(KhachHang kh) {
        kh.setMatKhau(passwordEncoder.encode(kh.getPassword()));
        khrp.saveAndFlush(kh);
    }

    @Override
    public void update(KhachHang kh) {
        khrp.save(kh);
    }

    @Override
    public List<KhachHang> getAll() {
        return khrp.findAll();
    }

    @Override
    public KhachHang getById(UUID id) {
        return khrp.findById(id).get();
    }

    @Override
    public void deleteById(UUID id) {
        khrp.deleteById(id);
    }

    @Override
    public boolean existById(UUID id) {
        return khrp.existsById(id);
    }

    @Override
    public Page<KhachHang> page(Pageable pageable) {
        return khrp.findAll(pageable);
    }

    @Override
    public List<KhachHang> sort(Sort sort) {
        return khrp.findAll(sort);
    }

    @Override
    public Page<KhachHang> getByName(String keyword, Pageable pageable) {
        return khrp.getByName(keyword, pageable);
    }

    @Override
    public Optional<KhachHang> findByNumberPhone(String number_phone) {
        return khrp.findByNumberPhone(number_phone);
    }

    @Override
    public Optional<KhachHang> findByEmail(String email) {
        return khrp.findByEmail(email);
    }

    @Override
    public String generateIDCustomer(KhachHang kh, List<KhachHang> listSort, List<KhachHang> listAll) {
        int index = Integer.parseInt(kh.getMaKhachHang().substring(2));
        kh.setMaKhachHang("KH" + listAll.size());
        for (KhachHang khachHang2 : listSort
        ) {
            if (khachHang2.getMaKhachHang().equalsIgnoreCase(kh.getMaKhachHang())) {
                int next = 1;
                kh.setMaKhachHang("KH" + (index + next));
            }
        }
        for (KhachHang khachHang2 : listSort
        ) {
            if (khachHang2.getMaKhachHang().equalsIgnoreCase(kh.getMaKhachHang())) {
                int next = 1;
                kh.setMaKhachHang("KH" + (index + next));
            }
        }
        return kh.getMaKhachHang();
    }

    @Override
    public String validation(KhachHang kh) {
        for (KhachHang khachHang : khrp.findAll()
        ) {
            if (khachHang.getEmail().equalsIgnoreCase(kh.getEmail())) {
                return "Email đã được người khác sử dụng!";
            }
            if (khachHang.getSdt().equalsIgnoreCase(kh.getSdt())) {
                return "Số điện thoại đã được người khác sử dụng!";
            }
        }
        for (NhanVien nhanVien : svnv.getAll()
        ) {
            if (nhanVien.getEmail().equalsIgnoreCase(kh.getEmail())) {
                return "Email đã được người khác sử dụng!";
            }
            if (nhanVien.getSdt().equalsIgnoreCase(kh.getSdt())) {
                return "Số điện thoại đã được người khác sử dụng!";
            }
        }
        return "success";
    }

    @Override
    public void add_author(Author_KhachHang author_khachHang) {
        auth.saveAndFlush(author_khachHang);
    }

    @Override
    public void delete_author(int id) {
        auth.deleteById(id);
    }

    @Override
    public Author_KhachHang getByRefreshToken(String refreshToken) {
        return auth.getByRefreshToken(refreshToken);
    }
}
