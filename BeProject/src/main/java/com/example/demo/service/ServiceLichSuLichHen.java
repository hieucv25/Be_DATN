package com.example.demo.service;

import com.example.demo.model.LichSuLichHen;

import java.util.List;
import java.util.UUID;

public interface ServiceLichSuLichHen {
    void save(LichSuLichHen ls);

    void update(LichSuLichHen ls);

    void deleteById(UUID id);

    List<LichSuLichHen> getAll();

    LichSuLichHen getById(UUID id);

    boolean existById(UUID id);

    int deleteByLh(UUID id);
}
