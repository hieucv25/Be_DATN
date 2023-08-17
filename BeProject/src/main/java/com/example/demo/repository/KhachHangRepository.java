package com.example.demo.repository;

import com.example.demo.model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    @Override
    <S extends KhachHang> S saveAndFlush(S entity);

    @Override
    List<KhachHang> findAll();

    @Override
    Optional<KhachHang> findById(UUID uuid);

    @Override
    void flush();

    @Override
    KhachHang getOne(UUID uuid);

    @Override
    KhachHang getById(UUID uuid);

    @Override
    <S extends KhachHang> S save(S entity);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    @Override
    Page<KhachHang> findAll(Pageable pageable);

    @Override
    List<KhachHang> findAll(Sort sort);

    @Query(value = "select kh from KhachHang kh where kh.hoTen like %:name% or kh.maKhachHang like %:name% order by kh.ngayTao desc ")
    Page<KhachHang> getByName(@Param("name") String keyword, Pageable pageable);

    @Query("select kh from KhachHang kh where kh.email =:email")
    Optional<KhachHang> findByEmail(@Param("email") String email);

    @Query("select kh from KhachHang kh where kh.sdt =:sdt")
    Optional<KhachHang> findByNumberPhone(@Param("sdt") String sdt);
}
