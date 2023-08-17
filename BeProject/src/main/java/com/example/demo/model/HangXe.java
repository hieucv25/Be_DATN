package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name="HangXe")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HangXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer Id;
    @Column(name="Ten")
    private String Ten;
    @Column(name="QuocGia")
    private String QuocGia;
    @Column(name="NgayTao")
    private LocalDateTime NgayTao;
    @Column(name="NgaySua")
    private LocalDateTime NgaySua;
}

