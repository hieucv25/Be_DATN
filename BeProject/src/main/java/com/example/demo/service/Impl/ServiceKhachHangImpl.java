package com.example.demo.service.Impl;

import com.example.demo.model.KhachHang;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.ServiceKhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceKhachHangImpl implements ServiceKhachHang {
    @Autowired
    KhachHangRepository khrp;

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
    public KhachHang findByNumberPhone(String number_phone) {
        return khrp.findByNumberPhone(number_phone).get();
    }
}
