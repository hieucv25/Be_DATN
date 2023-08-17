package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Table(name="KhachHang")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class KhachHang implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="Id")
    private UUID id;
    @Column(name="MaKhachHang",unique = true, length = 10)
    private String maKhachHang;
    @Column(name="HoTen", length = 255)
    private String hoTen;
    @Column(name="Email", length = 50,unique = true)
    private String email;
    @Column(name="SDT", length = 15, unique = true)
    private String sdt;
    @Column(name="Tinh_ThanhPho", length = 50)
    private String tinhThanh;
    @Column(name="Quan_Huyen", length = 50)
    private String quanHuyen;
    @Column(name="Xa_Phuong")
    private String xaPhuong;
    @Column(name="So_Nha")
    private String soNha;
    @Column(name="GioiTinh")
    private boolean gioiTinh;
    @Column(name="MatKhau")
    private String matKhau;
    @Column(name="MatKhauMaHoa")
    private String matKhauMaHoa;
    @Column(name="NgayTao")
    private LocalDateTime ngayTao;
    @Column(name="NgaySua")
    private LocalDateTime ngaySua;
    @Column(name="Anh")
    private String anh;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("CUSTOMER"));
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
