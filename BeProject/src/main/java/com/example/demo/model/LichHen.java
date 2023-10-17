package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="LichHen")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class LichHen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="Id")
    private UUID id;
    @Column(name="MaLichHen",unique = true)
    private String maLichHen;
    @Column(name="ThoiGianDat")
    private LocalDateTime thoiGianDat;
    @Column(name="SDT")
    private String sdt;
    @Column(name="TrangThai")
    private int trangThai;
    @Column(name="LoaiLichHen")
    private boolean loaiLichHen;
    @Column(name="ThoiGianDuKien")
    private String thoiGianDuKien;
    @Column(name="NgayTao")
    private LocalDateTime ngayTao;
    @Column(name="NgaySua")
    private LocalDateTime ngaySua;
    @ManyToOne
    @JoinColumn(name="IdKhachHang")
    private KhachHang kh;
}
