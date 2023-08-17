package com.example.demo.service.Impl;

import com.example.demo.model.NhanVien;
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

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public List<NhanVien> getAll() {
        return nvrp.getAll();
    }

    @Override
    public NhanVien getByEmail(String email) {
        return nvrp.findByEmail(email).get();
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
    public NhanVien findByNumberPhone(String number_phone) {
        return nvrp.findByNumberPhone(number_phone).get();
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
    public NhanVien finbyma(String maNhanVien) {
        return nvrp.finByMa(maNhanVien);
    }


    @Override
    public NhanVien findbyemail(String email) {
        return nvrp.finByEmail(email);
    }

    @Override
    public NhanVien findbysdt(String sdt) {
        return nvrp.finBysdt(sdt);
    }

    @Override
    public NhanVien findbycmnd(String cmnd) {
        return nvrp.finBycccd(cmnd);
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
}
