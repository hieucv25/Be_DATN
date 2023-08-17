package com.example.demo.repository;

import com.example.demo.model.LichHenChiTiet;
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
public interface LichHenChiTietRepository extends JpaRepository<LichHenChiTiet, UUID> {
    @Override
    <S extends LichHenChiTiet> S saveAndFlush(S entity);

    @Override
    List<LichHenChiTiet> findAll();

    @Override
    <S extends LichHenChiTiet> S save(S entity);

    @Override
    Optional<LichHenChiTiet> findById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    @Override
    Page<LichHenChiTiet> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("delete from LichHenChiTiet lhct where lhct.lh.id = :id")
    int deleteByLh(@Param("id") UUID id);
}
