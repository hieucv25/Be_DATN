package com.example.demo.service;

import com.example.demo.model.MauXe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceMauXe {
    List<MauXe> getAll();

    void insert(MauXe mauXe);

    Optional<MauXe> detail(UUID id);

    void delete(UUID Id);
}
