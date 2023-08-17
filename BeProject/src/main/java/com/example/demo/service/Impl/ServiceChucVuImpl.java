package com.example.demo.service.Impl;

import com.example.demo.model.ChucVu;
import com.example.demo.repository.ChucVuRepository;
import com.example.demo.service.ServiceChucVu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceChucVuImpl implements ServiceChucVu {

    @Autowired
    ChucVuRepository cvrp;

    @Override
    public List<ChucVu> getALl() {
        return cvrp.findAll();
    }

    @Override
    public ChucVu save(ChucVu cv) {
        return cvrp.saveAndFlush(cv);
    }

    @Override
    public Optional<ChucVu> findByID(Integer id) {
        return cvrp.findById(id);
    }

    @Override
    public ChucVu update(ChucVu cv) {
        return cvrp.save(cv);
    }

    @Override
    public void delete(Integer id) {
        cvrp.deleteById(id);
    }
}
