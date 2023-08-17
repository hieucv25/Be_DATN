package com.example.demo.repository;

import com.example.demo.model.HangXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HangXeRepository extends JpaRepository<HangXe,Integer> {

    @Override
    List<HangXe> findAll();

    @Override
    <S extends HangXe> S save(S entity);

    @Override
    Optional<HangXe> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
