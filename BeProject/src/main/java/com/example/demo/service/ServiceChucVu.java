package com.example.demo.service;

import com.example.demo.model.ChucVu;

import java.util.List;
import java.util.Optional;

public interface ServiceChucVu {

    List<ChucVu> getALl();

    ChucVu save(ChucVu cv);

    Optional<ChucVu> findByID(Integer id);

    ChucVu update(ChucVu cv);

    void delete(Integer id);
}
