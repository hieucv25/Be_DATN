package com.example.demo.repository;


import com.example.demo.model.LichSuSuDungPhuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface LichSuSuDungPhuKienRepository extends JpaRepository<LichSuSuDungPhuKien, UUID> {
    @Override
    List<LichSuSuDungPhuKien> findAll();

    @Override
    <S extends LichSuSuDungPhuKien> S save(S entity);

    @Override
    Optional<LichSuSuDungPhuKien> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);
}
