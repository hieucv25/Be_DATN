package com.example.demo.service;

import com.example.demo.model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceNhanVien {

    List<NhanVien> getAll();

    NhanVien getByEmail(String email);

    int deleteById(UUID id);

    boolean existsById(UUID id);

    NhanVien findByNumberPhone(String number_phone);

    List<NhanVien> getAllNV();

    void insert(NhanVien nhanVien);

    Optional<NhanVien> detail(UUID Id);

    void delete(UUID Id);

    Page<NhanVien> phanTrang(Pageable nv);

    NhanVien finbyma(String maNhanVien);

    NhanVien findbyemail(String email);

    NhanVien findbysdt(String sdt);

    NhanVien findbycmnd(String cmnd);

    List<NhanVien> findbychucvu(Integer chucVu);

    List<NhanVien> findbytt(Integer trangThai);

    List<NhanVien> timkiemnv(String keyword);

    List<NhanVien> khoangns(Date startDate, Date endDate);
}
