package com.example.demo.service;

import com.example.demo.model.MauSac;

import java.util.List;
import java.util.Optional;

public interface ServiceMauSac {
    List<MauSac> getAll();

    void insert(MauSac mauSac);

    Optional<MauSac> detail(Integer id);

    void delete(Integer id);
}
