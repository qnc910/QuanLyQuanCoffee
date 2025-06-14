/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author ADMIN
 */

@Entity
@Table(name = "loai_mon") 
public class LoaiMon {
    @Id 
    @Column(name = "ma_loai", length = 10)
    private String maLoai;
    
    @Column(name = "ten_loai", length = 100)
    private String tenLoai;
    
    @OneToMany(mappedBy = "maLoai", cascade = CascadeType.ALL) 
    private List<SanPham> danhsachSP;
    
    public LoaiMon() {
    }

    public LoaiMon(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    
    
}
