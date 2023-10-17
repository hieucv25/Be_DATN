package com.example.demo.service;

import com.example.demo.model.Author_KhachHang;
import com.example.demo.model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceKhachHang {
    void save(KhachHang kh);

    void update(KhachHang kh);

    List<KhachHang> getAll();

    KhachHang getById(UUID id);

    void deleteById(UUID id);

    boolean existById(UUID id);

    Page<KhachHang> page(Pageable pageable);

    List<KhachHang> sort(Sort sort);

    Page<KhachHang> getByName(String keyword, Pageable pageable);

    Optional<KhachHang> findByNumberPhone(String number_phone);

    Optional<KhachHang> findByEmail(String email);

    String generateIDCustomer(KhachHang kh, List<KhachHang> listSort, List<KhachHang> listAll);

    String validation(KhachHang kh);

    void add_author(Author_KhachHang author_khachHang);

    void delete_author(int id);

    Author_KhachHang getByRefreshToken(String refreshToken);
}
