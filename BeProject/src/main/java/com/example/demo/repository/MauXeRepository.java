package com.example.demo.repository;

import com.example.demo.model.MauXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MauXeRepository extends JpaRepository<MauXe, UUID> {
    @Override
    List<MauXe> findAll();

    @Override
    <S extends MauXe> S save(S entity);

    @Override
    Optional<MauXe> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);
}
