package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NhaCungCap")
@Getter
@Setter
public class NhaCungCap {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="Ten")
    private String Ten;
    @Column(name="ThanhPho")
    private String ThanhPho;
    @Column(name="QuocGia")
    private String QuocGia;
    @Column(name="DiaChi")
    private String DiaChi;
    @Column(name="NgayTao")
    private LocalDateTime NgayTao;
    @Column(name="NgaySua")
    private LocalDateTime NgaySua;
}
