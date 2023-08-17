package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="PhuKien")
public class PhuKien {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    @Column(name="MaPhuKien")
    private String MaPhuKien;
    @Column(name="TenPhuKien")
    private String TenPhuKien;
    @Column(name="Gia")
    private BigDecimal Gia;
    @Column(name="SoLuongTon")
    private Integer SoLuongTon;
    @Column(name="NgayTao")
    private LocalDateTime NgayTao;
    @Column(name="NgaySua")
    private LocalDateTime NgaySua;
    @ManyToOne
    @JoinColumn(name="IdNhaCungCap")
    private NhaCungCap NhaCungCap;
    @Column(name="TrangThai")
    private Integer TrangThai;
    @Column(name="Anh")
    private String image;
}