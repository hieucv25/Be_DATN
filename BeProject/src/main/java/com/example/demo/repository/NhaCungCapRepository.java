package com.example.demo.repository;

import com.example.demo.model.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap,Integer> {
    @Override
    List<NhaCungCap> findAll();

    @Override
    <S extends NhaCungCap> S save(S entity);

    @Override
    Optional<NhaCungCap> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
