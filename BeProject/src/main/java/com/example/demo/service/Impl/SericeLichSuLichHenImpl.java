package com.example.demo.service.Impl;

import com.example.demo.model.LichSuLichHen;
import com.example.demo.repository.LichSuLichHenRepository;
import com.example.demo.service.ServiceLichSuLichHen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SericeLichSuLichHenImpl implements ServiceLichSuLichHen {
    @Autowired
    LichSuLichHenRepository rp;

    @Override
    public void save(LichSuLichHen ls) {
        rp.saveAndFlush(ls);
    }

    @Override
    public void update(LichSuLichHen ls) {
        rp.save(ls);
    }

    @Override
    public void deleteById(UUID id) {
        rp.deleteById(id);
    }

    @Override
    public List<LichSuLichHen> getAll() {
        return rp.findAll();
    }

    @Override
    public LichSuLichHen getById(UUID id) {
        return rp.findById(id).get();
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
