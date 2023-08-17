package com.example.demo.repository;

import com.example.demo.model.LichHen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LichHenRepository extends JpaRepository<LichHen, UUID> {
    @Override
    <S extends LichHen> S saveAndFlush(S entity);

    @Override
    List<LichHen> findAll();

    @Override
    LichHen getOne(UUID uuid);

    @Override
    LichHen getById(UUID uuid);

    @Override
    <S extends LichHen> S save(S entity);

    @Override
    Optional<LichHen> findById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    @Override
    List<LichHen> findAll(Sort sort);

    @Override
    Page<LichHen> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("delete from LichHen lh where lh.kh.id = :id")
    int deleteByKH(@Param("id") UUID id);

    @Query("select lh from LichHen lh where lh.kh.id = :id")
    List<LichHen> getByKH(@Param("id") UUID id);

    @Query("select lh from LichHen lh where lh.maLichHen = :ma")
    LichHen existsByMa(@Param("ma") String ma);

    @Query("select lh from LichHen lh where lh.trangThai = :tt")
    Page<LichHen> findByStatus(@Param("tt") int tt, Pageable pageable);

    @Query("select lh from LichHen lh where lh.loaiLichHen = :type")
    Page<LichHen> findByType(@Param("type") boolean type, Pageable pageable);

    @Query("select lh from LichHen lh where lh.loaiLichHen = :type and lh.trangThai = :tt")
    Page<LichHen> findByStatusAndType(@Param("type") boolean type, @Param("tt") int tt, Pageable pageable);

    @Query("select lh from LichHen lh where lh.kh.hoTen like %:kw% or lh.maLichHen like %:kw%")
    Page<LichHen> findByName(@Param("kw") String kw, Pageable pageable);

    @Query("select lh from LichHen lh where lh.kh.hoTen like %:kw% or lh.maLichHen like %:kw% and lh.trangThai = :tt")
    Page<LichHen> findByNameAndStatus(@Param("kw") String kw, @Param("tt") int tt, Pageable pageable);

    @Query("select lh from LichHen lh where lh.kh.hoTen like %:kw% or lh.maLichHen like %:kw% and lh.loaiLichHen = :type")
    Page<LichHen> findByNameAndType(@Param("kw") String kw, @Param("type") boolean type, Pageable pageable);

    @Query("select lh from LichHen lh where lh.kh.hoTen like %:kw% or lh.maLichHen like %:kw% and lh.loaiLichHen = :type and lh.trangThai = :tt")
    Page<LichHen> findBy3Properties(@Param("kw") String kw, @Param("tt") int tt, @Param("type") boolean type, Pageable pageable);
}
