package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="LichSuLichHen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LichSuLichHen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="Id")
    private UUID id;
    @Column(name="TrangThai")
    private int trangThai;
    @ManyToOne
    @JoinColumn(name="IdLichHen")
    private LichHen lh;
    @Column(name="NgayTao")
    private LocalDateTime ngayTao;
    @Column(name="NgaySua")
    private LocalDateTime ngaySua;
}
