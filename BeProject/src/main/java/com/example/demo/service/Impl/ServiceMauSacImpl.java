package com.example.demo.service.Impl;

import com.example.demo.model.MauSac;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.service.ServiceMauSac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceMauSacImpl implements ServiceMauSac {
    @Autowired
    MauSacRepository rp;
    @Override
    public List<MauSac> getAll() {
        return rp.findAll();
    }

    @Override
    public void insert(MauSac mauSac) {
        rp.save(mauSac);
    }

    @Override
    public Optional<MauSac> detail(Integer id) {
        return rp.findById(id);
    }

    @Override
    public void delete(Integer id) {
        rp.deleteById(id);
    }
}
