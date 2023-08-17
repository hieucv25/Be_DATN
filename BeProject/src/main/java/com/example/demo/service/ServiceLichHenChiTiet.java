package com.example.demo.service;

import com.example.demo.model.LichHenChiTiet;

import java.util.List;
import java.util.UUID;

public interface ServiceLichHenChiTiet {
    void save(LichHenChiTiet lhct);

    void update(LichHenChiTiet lhct);

    List<LichHenChiTiet> getAll();

    LichHenChiTiet getById(UUID id);

    void deleteById(UUID id);

    boolean existById(UUID id);

    int deleteByLh(UUID id);
}
