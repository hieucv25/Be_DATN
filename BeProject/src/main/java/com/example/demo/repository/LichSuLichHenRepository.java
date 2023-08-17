package com.example.demo.repository;

import com.example.demo.model.LichSuLichHen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LichSuLichHenRepository extends JpaRepository<LichSuLichHen, UUID> {
    @Override
    <S extends LichSuLichHen> S saveAndFlush(S entity);

    @Override
    List<LichSuLichHen> findAll();

    @Override
    <S extends LichSuLichHen> S save(S entity);

    @Override
    Optional<LichSuLichHen> findById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    @Override
    Page<LichSuLichHen> findAll(Pageable pageable);

    @Query("select lh from LichSuLichHen lh where lh.lh.id = :id")
    List<LichSuLichHen> getByL(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("delete from LichSuLichHen lh where lh.lh.id = :id")
    int deleteByLh(@Param("id") UUID id);
}
