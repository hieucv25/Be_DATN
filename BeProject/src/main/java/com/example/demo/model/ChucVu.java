package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="ChucVu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChucVu {
    @Id
    @Column(name="MaChucVu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maChucVu;
    @Column(name="TenChucVu")
    private String tenChucVu;
    @Column(name="NgayTao")
    private LocalDateTime ngayTao;
    @Column(name="NgaySua")
    private LocalDateTime ngaySua;

}
