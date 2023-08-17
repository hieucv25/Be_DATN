package com.example.demo.service.Impl;

import com.example.demo.model.LichHenChiTiet;
import com.example.demo.repository.LichHenChiTietRepository;
import com.example.demo.service.ServiceLichHenChiTiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceLichHenChiTietImpl implements ServiceLichHenChiTiet {
    @Autowired
    LichHenChiTietRepository rp;

    @Override
    public void save(LichHenChiTiet lhct) {
        rp.saveAndFlush(lhct);
    }

    @Override
    public void update(LichHenChiTiet lhct) {
        rp.save(lhct);
    }

    @Override
    public List<LichHenChiTiet> getAll() {
        return rp.findAll();
    }

    @Override
    public LichHenChiTiet getById(UUID id) {
        return rp.findById(id).get();
    }

    @Override
    public void deleteById(UUID id) {
        rp.deleteById(id);
    }

    @Override
    public boolean existById(UUID id) {
        return rp.existsById(id);
    }

    @Override
    public int deleteByLh(UUID id) {
        return rp.deleteByLh(id);
    }
}
