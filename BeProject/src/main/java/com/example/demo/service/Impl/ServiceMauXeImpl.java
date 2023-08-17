package com.example.demo.service.Impl;

import com.example.demo.model.MauXe;
import com.example.demo.repository.MauXeRepository;
import com.example.demo.service.ServiceMauXe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceMauXeImpl implements ServiceMauXe {
    @Autowired
    MauXeRepository rp;
    @Override
    public List<MauXe> getAll() {
        return rp.findAll();
    }

    @Override
    public void insert(MauXe mauXe) {
        rp.save(mauXe);
    }

    @Override
    public Optional<MauXe> detail(UUID id) {
        return rp.findById(id);
    }

    @Override
    public void delete(UUID id) {
        rp.deleteById(id);
    }
}
