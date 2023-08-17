package com.example.demo.service;

import com.example.demo.model.HangXe;

import java.util.List;
import java.util.Optional;

public interface ServiceHangXe {
    List<HangXe> getAll();

    void insert(HangXe hangXe);

    Optional<HangXe> detail(Integer id);

    void delete(Integer id);
}
