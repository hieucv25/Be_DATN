package com.example.demo.repository;

import com.example.demo.model.PhuKien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PhuKienRepository extends JpaRepository<PhuKien, UUID> {
    @Override
    Page<PhuKien> findAll(Pageable pageable);
    @Query("Select pk From PhuKien pk Where pk.MaPhuKien like :maPhuKien")
    PhuKien findbyma(@Param("maPhuKien") String maPhuKien);
    @Query("Select pk From PhuKien pk Where pk.NhaCungCap.Id = :nhaCungCap")
   List<PhuKien> findbyncc(@Param("nhaCungCap") Integer nhaCungCap);
    @Query("Select pk From PhuKien pk Where pk.TrangThai = :trangThai")
    List<PhuKien> findbytt(@Param("trangThai") Integer trangThai);
    @Query("Select pk From PhuKien pk Where pk.Gia = :gia")
    List<PhuKien> findbygia(@Param("gia") Double gia );
   @Query("Select pk From PhuKien  pk where pk.MaPhuKien Like :keyword or pk.TenPhuKien Like :keyword")
    List<PhuKien> timkiem(@Param("keyword")String keyword);
   @Modifying
    @Query(value = "Update PhuKien set TrangThai = 1 where Id =?",nativeQuery = true)
    void deleteById(UUID id);
   @Query(value = "select*from PhuKien p WHERE p.TrangThai =0",nativeQuery = true)
    List<PhuKien> getAll();
}
