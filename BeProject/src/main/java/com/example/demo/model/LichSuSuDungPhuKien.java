package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="LichSuSuDungPhuKien")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LichSuSuDungPhuKien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private UUID Id;
    @Column(name="SoPhuKien")
    private Integer SoPhuKien;
    @ManyToOne
    @JoinColumn(name="IdPhuKien")
    private PhuKien PhuKien;
    @Column(name="NgayTao")
    private LocalDate NgayTao;
    @Column(name="NgaySua")
    private LocalDate NgaySua;
    @ManyToOne
    @JoinColumn(name="IdLichHen")
    private LichHen LichHen;
}