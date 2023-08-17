package com.example.demo.service;

import com.example.demo.model.LichSuSuDungPhuKien;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceLichSuSDPK {
    List<LichSuSuDungPhuKien> getAll();

    void insert(LichSuSuDungPhuKien lichSuSuDungPhuKien);

    Optional<LichSuSuDungPhuKien> detail(UUID Id);

    void delete(UUID Id);
}
