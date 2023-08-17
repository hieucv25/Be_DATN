package com.example.demo.repository;

import com.example.demo.model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {
    @Override
    List<ChucVu> findAll();

    @Override
    <S extends ChucVu> S saveAndFlush(S entity);

    @Override
    <S extends ChucVu> S save(S entity);

    @Override
    Optional<ChucVu> findById(Integer integer);

    Optional<ChucVu> findByTenChucVu(String cv);

    @Override
    void deleteById(Integer integer);
}
