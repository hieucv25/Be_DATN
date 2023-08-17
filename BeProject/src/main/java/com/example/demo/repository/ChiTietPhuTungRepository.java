package com.example.demo.repository;


import com.example.demo.model.ChiTietPhuTung;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ChiTietPhuTungRepository extends JpaRepository<ChiTietPhuTung, UUID> {
    @Override
    <S extends ChiTietPhuTung> List<S> findAll(Example<S> example);

    @Override
    <S extends ChiTietPhuTung> S save(S entity);

    @Override
    Optional<ChiTietPhuTung> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);
}
