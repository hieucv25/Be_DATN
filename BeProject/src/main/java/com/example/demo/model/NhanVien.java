package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name="NhanVien")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NhanVien implements UserDetails {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="MaNhanVien",unique = true)
    private String maNhanVien;
    @Column(name="HoTen")
    private String hoTen;
    @Column(name="Tinh_ThanhPho")
    private String tinhThanh;
    @Column(name="Quan_Huyen")
    private String quanHuyen;
    @Column(name="Xa_Phuong")
    private String xaPhuong;
    @Column(name="So_Nha")
    private String soNha;
    @Column(name="Email",unique = true)
    private String email;
    @Column(name="SDT",unique = true)
    private String sdt;
    @Column(name="TrangThai")
    private int trangThai;
    @Column(name="MatKhau")
    private String matKhau;
    @Column(name="MatKhauMaHoa")
    private String matKhauMaHoa;
    @Column(name="NgaySinh")
    private String ngaySinh;
    @Column(name="CCCD")
    private String cccd;
    @Column(name="Anh")
    private String anh;
    @Column(name="NgayTao")
    private LocalDateTime ngayTao;
    @Column(name="NgaySua")
    private LocalDateTime ngaySua;
    @ManyToOne
    @JoinColumn(name="ChucVu")
    private ChucVu cv;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(cv.getTenChucVu()));
    }

    @Override
    public String getPassword() {
        return matKhauMaHoa;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
