package com.example.demo.service;

import com.example.demo.model.NhaCungCap;

import java.util.List;
import java.util.Optional;

public interface ServiceNhaCungCap {
    List<NhaCungCap> getAll();

    void insert(NhaCungCap nhaCungCap);

    Optional<NhaCungCap> detail(Integer id);

    void delete(Integer Id);
}
