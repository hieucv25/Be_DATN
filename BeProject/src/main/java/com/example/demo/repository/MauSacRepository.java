package com.example.demo.repository;


import com.example.demo.model.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Integer> {
    @Override
    List<MauSac> findAll();

    @Override
    <S extends MauSac> S save(S entity);

    @Override
    Optional<MauSac> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
