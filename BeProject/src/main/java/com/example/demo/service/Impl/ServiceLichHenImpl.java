package com.example.demo.service.Impl;

import com.example.demo.model.LichHen;
import com.example.demo.repository.LichHenRepository;
import com.example.demo.service.ServiceLichHen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceLichHenImpl implements ServiceLichHen {
    @Autowired
    LichHenRepository lhrp;

    @Override
    public void save(LichHen lh) {
        lhrp.saveAndFlush(lh);
    }

    @Override
    public void update(LichHen lh) {
        lhrp.save(lh);
    }

    @Override
    public List<LichHen> getAll() {
        return lhrp.findAll();
    }

    @Override
    public LichHen getById(UUID id) {
        return lhrp.findById(id).get();
    }

    @Override
    public void deleteById(UUID id) {
        lhrp.deleteById(id);
    }

    @Override
    public LichHen getByIdOp(UUID id) {
        return lhrp.findById(id).get();
    }

    @Override
    public boolean existById(UUID id) {
        return lhrp.existsById(id);
    }

    @Override
    public List<LichHen> getByKH(UUID id) {
        return lhrp.getByKH(id);
    }

    @Override
    public int deleteByKh(UUID id) {
        return lhrp.deleteByKH(id);
    }

    @Override
    public Page<LichHen> paging(Pageable pageable) {
        return lhrp.findAll(pageable);
    }

    @Override
    public List<LichHen> findAllAndSort(Sort sort) {
        return lhrp.findAll(sort);
    }

    @Override
    public LichHen existsByMa(String ma) {
        return lhrp.existsByMa(ma);
    }

    @Override
    public Page<LichHen> findByStatus(int status, Pageable pageable) {
        return lhrp.findByStatus(status, pageable);
    }

    @Override
    public Page<LichHen> findByType(boolean type, Pageable pageable) {
        return lhrp.findByType(type, pageable);
    }

    @Override
    public Page<LichHen> findByStatusAndType(int status, boolean type, Pageable pageable) {
        return lhrp.findByStatusAndType(type, status, pageable);
    }

    @Override
    public Page<LichHen> findByName(String keyword, Pageable pageable) {
        return lhrp.findByName(keyword, pageable);
    }

}
