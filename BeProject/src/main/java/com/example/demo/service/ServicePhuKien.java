package com.example.demo.service;

import com.example.demo.model.PhuKien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServicePhuKien {
    List<PhuKien> getAll();

    void insert(PhuKien phuKien);

    Optional<PhuKien> detail(UUID Id);

    void delete(UUID Id);

    Page<PhuKien> phanTrang(Pageable nv);

    List<PhuKien> findbygia(Double gia);

    PhuKien findbyma(String maPhuKien);

    List<PhuKien> findbyncc(Integer nhaCungCap);

    List<PhuKien> findbytt(Integer trangThai);

    List<PhuKien> timkiempk(String keyword);
}
