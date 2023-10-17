package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Author_KhachHang")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Author_KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Refresh_Token")
    private String refreshToken;
    @Column(name = "ExpirationTime")
    private LocalDateTime expirationTime;
    @ManyToOne
    @JoinColumn(name = "KhachHang")
    private KhachHang khachHang;

    public boolean isEmty() {
        return refreshToken == null && expirationTime == null;
    }
}
