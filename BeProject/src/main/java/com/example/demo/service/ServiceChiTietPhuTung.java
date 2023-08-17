package com.example.demo.service;

import com.example.demo.model.ChiTietPhuTung;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceChiTietPhuTung {
    List<ChiTietPhuTung> getAll();

    void insert(ChiTietPhuTung chiTietPhuTung);

    Optional<ChiTietPhuTung> detail(UUID id);

    void delete(UUID id);
}
