/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 *
 * @author HELLO
 */
@Entity
@Table(name = "chi_tiet_hoa_don")
@IdClass(ChiTietHoaDonId.class)
public class ChiTietHoaDon {

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_hd")
    private HoaDon hoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_mon")
    private SanPham sanPham;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "gia_luc_ban")
    private BigDecimal giaLucBan;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaLucBan() {
        return giaLucBan;
    }

    public void setGiaLucBan(BigDecimal giaLucBan) {
        this.giaLucBan = giaLucBan;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

  
}



