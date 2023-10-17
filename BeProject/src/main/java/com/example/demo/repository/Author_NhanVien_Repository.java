package com.example.demo.repository;

import com.example.demo.model.Author_NhanVien;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Author_NhanVien_Repository extends JpaRepository<Author_NhanVien,Integer> {
    @Override
    <S extends Author_NhanVien> S saveAndFlush(S entity);

    @Override
    <S extends Author_NhanVien> List<S> findAll(Example<S> example);

    @Override
    <S extends Author_NhanVien> S save(S entity);

    @Override
    Optional<Author_NhanVien> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Query("select auth from Author_NhanVien auth where auth.refreshToken =: refreshToken")
    Author_NhanVien getByRefreshToken(@Param("refreshToken") String refreshToken);

}
