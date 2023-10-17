package com.example.demo.repository;


import com.example.demo.model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Override
    <S extends NhanVien> S saveAndFlush(S entity);

    @Override
    <S extends NhanVien> S save(S entity);

    @Override
    Optional<NhanVien> findById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    @Override
    List<NhanVien> findAll();

    @Query("select nv from NhanVien nv where nv.email =:email")
    Optional<NhanVien> findByEmail(@Param("email") String email);

    @Query("select nv from NhanVien nv where nv.sdt =:sdt")
    Optional<NhanVien> findByNumberPhone(@Param("sdt") String sdt);

    @Transactional
    @Modifying
    @Query("delete from NhanVien nv where nv.id =:id")
    int deleteByIdQuery(@Param("id") UUID id);

    @Override
    Page<NhanVien> findAll(Pageable pageable);

    @Query("Select nv From NhanVien nv Where nv.maNhanVien like :maNhanVien")
    NhanVien finByMa(@Param("maNhanVien") String maNhanVien);

    @Query("Select nv From NhanVien nv Where nv.email like :email")
    Optional<NhanVien> finByEmail(@Param("email") String email);

    @Query("Select nv From NhanVien nv Where nv.sdt like :sdt")
    Optional<NhanVien> finBySDT(@Param("sdt") String sdt);

    @Query("Select nv From NhanVien nv Where nv.cccd like :cmnd")
    Optional<NhanVien> finByCCCD(@Param("cmnd") String cccd);

    @Query("Select nv From NhanVien nv Where nv.cv.maChucVu = :chucVu")
    List<NhanVien> findByChucVu(Integer chucVu);

    @Query("Select nv From NhanVien nv Where nv.trangThai = :trangThai")
    List<NhanVien> findBytt(Integer trangThai);

    @Query("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien LIKE :keyword OR nv.hoTen LIKE :keyword OR nv.sdt like :keyword or nv.email like :keyword")
    List<NhanVien> timKiem(@Param("keyword") String keyword);

    @Query("Select nv From NhanVien  nv WHERE nv.ngaySinh between :startDate and :endDate")
    List<NhanVien> findngaysinh(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "Select*from NhanVien n where n.TrangThai=0", nativeQuery = true)
    List<NhanVien> getAll();

    @Modifying
    @Query(value = "Update NhanVien set TrangThai=1 where Id=?",nativeQuery = true)
    void deleteSoftById(UUID id);
}
