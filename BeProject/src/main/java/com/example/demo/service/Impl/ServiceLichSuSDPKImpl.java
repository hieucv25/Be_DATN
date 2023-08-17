package com.example.demo.service.Impl;

import com.example.demo.model.LichSuSuDungPhuKien;
import com.example.demo.repository.LichSuSuDungPhuKienRepository;
import com.example.demo.service.ServiceLichSuSDPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ServiceLichSuSDPKImpl implements ServiceLichSuSDPK {
    @Autowired
    LichSuSuDungPhuKienRepository rp;
    @Override
    public List<LichSuSuDungPhuKien> getAll() {
        return rp.findAll();
    }

    @Override
    public void insert(LichSuSuDungPhuKien lichSuSuDungPhuKien) {
        rp.save(lichSuSuDungPhuKien);
    }

    @Override
    public Optional<LichSuSuDungPhuKien> detail(UUID id) {
        return rp.findById(id);
    }

    @Override
    public void delete(UUID id) {
        rp.deleteById(id);
    }
}
