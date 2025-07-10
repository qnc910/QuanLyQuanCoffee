/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author HELLO
 */
    @Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @Column(name = "ma_hd")
    private String maHd;

    @Column(name = "ngay_lap")
    private LocalDate ngayLap;

    @Column(name = "gio")
    private LocalTime gio;

    @Column(name = "ma_nv")
    private String maNv;

    @Column(name = "trang_thai")
    private String trangThai;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDon;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<BanHoaDon> banHoaDons;

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public LocalTime getGio() {
        return gio;
    }

    public void setGio(LocalTime gio) {
        this.gio = gio;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietHoaDon> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }

    public List<BanHoaDon> getBanHoaDons() {
        return banHoaDons;
    }

    public void setBanHoaDons(List<BanHoaDon> banHoaDons) {
        this.banHoaDons = banHoaDons;
    }
}


