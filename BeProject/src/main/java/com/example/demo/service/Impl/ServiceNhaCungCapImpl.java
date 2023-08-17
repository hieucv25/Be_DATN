package com.example.demo.service.Impl;

import com.example.demo.model.NhaCungCap;
import com.example.demo.repository.NhaCungCapRepository;
import com.example.demo.service.ServiceNhaCungCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceNhaCungCapImpl implements ServiceNhaCungCap {
    @Autowired
    NhaCungCapRepository rp;

    @Override
    public List<NhaCungCap> getAll() {
        return rp.findAll();
    }

    @Override
    public void insert(NhaCungCap nhaCungCap) {
        rp.save(nhaCungCap);
    }

    @Override
    public Optional<NhaCungCap> detail(Integer id) {
        return rp.findById(id);
    }

    @Override
    public void delete(Integer id) {
        rp.deleteById(id);
    }
}
