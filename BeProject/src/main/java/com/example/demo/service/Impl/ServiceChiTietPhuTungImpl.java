package com.example.demo.service.Impl;

import com.example.demo.model.ChiTietPhuTung;
import com.example.demo.repository.ChiTietPhuTungRepository;
import com.example.demo.service.ServiceChiTietPhuTung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceChiTietPhuTungImpl implements ServiceChiTietPhuTung {
    @Autowired
    ChiTietPhuTungRepository rp;
    @Override
    public List<ChiTietPhuTung> getAll() {
        return rp.findAll();
    }

    @Override
    public void insert(ChiTietPhuTung chiTietPhuTung) {
        rp.save(chiTietPhuTung);
    }

    @Override
    public Optional<ChiTietPhuTung> detail(UUID id) {
        return rp.findById(id);
    }

    @Override
    public void delete(UUID id) {
        rp.deleteById(id);
    }
}
