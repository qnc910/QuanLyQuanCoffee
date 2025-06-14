/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "san_pham") 
public class SanPham {
    
    @Id
    @Column(name = "ma_mon", length = 10)
    private String maMon ;
    
    @Column(name = "ten_mon", length = 10)
    private String tenMon;
    
    @Column(name = "gia")
    private long gia;
    
    @ManyToOne
    @JoinColumn(name = "ma_loai")
    private LoaiMon maLoai;
    
    @Column(name = "hinh_anh", length = 255)
    private String hinhAnh;
    
    

    public SanPham() {
    }

    public SanPham(String maMon, String tenMon, long gia, LoaiMon maLoai, String hinhAnh) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.gia = gia;
        this.maLoai = maLoai;
        this.hinhAnh = hinhAnh;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }
   

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public LoaiMon getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(LoaiMon maLoai) {
        this.maLoai = maLoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    
}
