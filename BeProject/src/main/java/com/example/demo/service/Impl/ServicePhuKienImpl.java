package com.example.demo.service.Impl;

import com.example.demo.model.PhuKien;
import com.example.demo.repository.PhuKienRepository;
import com.example.demo.service.ServicePhuKien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicePhuKienImpl implements ServicePhuKien {
    @Autowired
    PhuKienRepository phuKienRepository;

    @Override
    public List<PhuKien> getAll() {
        return phuKienRepository.getAll();
    }

    @Override
    public void insert(PhuKien phuKien) {
        phuKienRepository.save(phuKien);
    }

    @Override
    public Optional<PhuKien> detail(UUID Id) {
        return phuKienRepository.findById(Id);
    }

    @Override
    public void delete(UUID Id) {
        phuKienRepository.deleteById(Id);
    }

    @Override
    public Page<PhuKien> phanTrang(Pageable nv) {
        return phuKienRepository.findAll(nv);
    }

    @Override
    public List<PhuKien> findbygia(Double gia) {
        return phuKienRepository.findbygia(gia);
    }


    @Override
    public PhuKien findbyma(String maPhuKien) {
        return phuKienRepository.findbyma(maPhuKien);
    }

    @Override
    public List<PhuKien> findbyncc(Integer nhaCungCap) {
        List<PhuKien>list=phuKienRepository.findbyncc(nhaCungCap);
        return list;
    }

    @Override
    public List<PhuKien> findbytt(Integer trangThai) {
        List<PhuKien> list=phuKienRepository.findbytt(trangThai);
        return list;
    }

    @Override
    public List<PhuKien> timkiempk(String keyword) {
        return phuKienRepository.timkiem(keyword);
    }
}
