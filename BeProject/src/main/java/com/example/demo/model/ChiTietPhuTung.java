package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="ChiTietPhuTung")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChiTietPhuTung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private UUID Id;
    @Column(name="SoLuong")
    private Integer SoLuong;
    @ManyToOne
    @JoinColumn(name="IdPhuKien")
    private PhuKien PhuKien;
    @ManyToOne
    @JoinColumn(name="IdMauXe")
    private MauXe MauXe;
    @Column(name="NgayTao")
    private LocalDateTime NgayTao;
    @Column(name="NgaySua")
    private LocalDateTime NgaySua;
}

