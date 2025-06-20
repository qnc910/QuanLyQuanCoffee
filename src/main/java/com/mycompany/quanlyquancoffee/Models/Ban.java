/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.*;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "ban")
public class Ban {
    @Id
    @Column(name = "ma_ban", length = 10)
    private String maBan;
    
    @Column(name = "ten_ban", length = 100)
    private String tenBan;
    
    @ManyToOne
    @JoinColumn(name = "ma_kv")
    private KhuVuc khuvuc = new KhuVuc();
    
    @Column(name = "trang_thai", length = 20)
    private String trangThai;

    public Ban() {
    }

    public Ban(String maBan, String tenBan,String maKV, String trangThai) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.khuvuc = new KhuVuc();
        this.khuvuc.setMaKV(maKV);
        this.trangThai = trangThai;
    }
    
    public String getTenKV(){
        return khuvuc != null ? khuvuc.getTenKV(): "";
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getMaKV() {
        return khuvuc.getMaKV();
    }

    public void setKhuvuc(KhuVuc khuvuc) {
        this.khuvuc = khuvuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
