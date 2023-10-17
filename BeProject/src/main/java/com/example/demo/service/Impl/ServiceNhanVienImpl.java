package com.example.demo.service.Impl;

import com.example.demo.model.Author_NhanVien;
import com.example.demo.model.KhachHang;
import com.example.demo.model.NhanVien;
import com.example.demo.repository.Author_NhanVien_Repository;
import com.example.demo.repository.ChucVuRepository;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.service.ServiceNhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceNhanVienImpl implements ServiceNhanVien {

    @Autowired
    NhanVienRepository nvrp;

    @Autowired
    ChucVuRepository cvrp;

    @Autowired
    Author_NhanVien_Repository auth;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public List<NhanVien> getAll() {
        return nvrp.getAll();
    }

    @Override
    public int deleteById(UUID id) {
        return nvrp.deleteByIdQuery(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return nvrp.existsById(id);
    }

    @Override
    public Optional<NhanVien> findByNumberPhone(String number_phone) {
        return nvrp.findByNumberPhone(number_phone);
    }

    @Override
    public List<NhanVien> getAllNV() {
        return nvrp.findAll();
    }

    @Override
    public void insert(NhanVien nhanVien) {
        nhanVien.setMatKhauMaHoa(passwordEncoder.encode(nhanVien.getMatKhau()));
        nvrp.save(nhanVien);
    }

    @Override
    public Optional<NhanVien> detail(UUID Id) {
        return nvrp.findById(Id);
    }

    @Override
    public void delete(UUID Id) {
        nvrp.deleteSoftById(Id);
    }

    @Override
    public Page<NhanVien> phanTrang(Pageable nv) {
        return nvrp.findAll(nv);
    }

    @Override
    public NhanVien findByMa(String maNhanVien) {
        return nvrp.finByMa(maNhanVien);
    }


    @Override
    public Optional<NhanVien> findByEmail(String email) {
        return nvrp.finByEmail(email);
    }

    @Override
    public Optional<NhanVien> findBySDT(String sdt) {
        return nvrp.finBySDT(sdt);
    }

    @Override
    public Optional<NhanVien> findByCCCD(String cmnd) {
        return nvrp.finByCCCD(cmnd);
    }

    @Override
    public List<NhanVien> findbychucvu(Integer chucVu) {
        List<NhanVien>list=nvrp.findByChucVu(chucVu);
        return list;
    }

    @Override
    public List<NhanVien> findbytt(Integer trangThai) {
        List<NhanVien>list=nvrp.findBytt(trangThai);
        return list;
    }

    @Override
    public List<NhanVien> timkiemnv(String keyword) {
        return nvrp.timKiem(keyword);
    }

    @Override
    public List<NhanVien> khoangns(java.sql.Date startDate, Date endDate) {
        return nvrp.findngaysinh(startDate,endDate);
    }

    @Override
    public void add_author(Author_NhanVien author_nhanVien) {
        auth.saveAndFlush(author_nhanVien);
    }

    @Override
    public void delete_author(int id) {
        auth.deleteById(id);
    }

    @Override
    public Author_NhanVien getByRefreshToken(String refreshToken) {
        return auth.getByRefreshToken(refreshToken);
    }
}
