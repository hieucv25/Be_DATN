package com.example.demo.repository;

import com.example.demo.model.Author_KhachHang;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Author_KhachHang_Repository extends JpaRepository<Author_KhachHang,Integer> {
    @Override
    <S extends Author_KhachHang> S saveAndFlush(S entity);

    @Override
    <S extends Author_KhachHang> List<S> findAll(Example<S> example);

    @Override
    <S extends Author_KhachHang> S save(S entity);

    @Override
    Optional<Author_KhachHang> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Query("select auth from Author_KhachHang auth where auth.refreshToken =: refreshToken")
    Author_KhachHang getByRefreshToken(@Param("refreshToken") String refreshToken);
}
