package com.example.demo.service.Impl;

import com.example.demo.model.HangXe;
import com.example.demo.repository.HangXeRepository;
import com.example.demo.service.ServiceHangXe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceHangXeImpl implements ServiceHangXe {

    @Autowired
    HangXeRepository rp;

    @Override
    public List<HangXe> getAll() {
        return rp.findAll();
    }

    @Override
    public void insert(HangXe hangXe) {
        rp.save(hangXe);
    }

    @Override
    public Optional<HangXe> detail(Integer id) {
        return rp.findById(id);
    }

    @Override
    public void delete(Integer id) {
        rp.deleteById(id);
    }
}
