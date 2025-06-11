/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

/**
 *
 * @author HELLO
 */

import jakarta.persistence.*;  // hoặc javax.persistence.* nếu bạn dùng Spring Boot < 3

@Entity
@Table(name = "tai_khoan")  // Trùng với tên bảng trong CSDL
public class TaiKhoan {

    @Id
    @Column(name = "ten_dang_nhap", length = 50)
    private String tenDangNhap;

    @Column(name = "mat_khau", nullable = false, length = 50)
    private String matKhau;

    @Column(name = "ma_nv", length = 10)
    private String maNV;

    @Column(name = "quyen", length = 20)
    private String quyen;

    // Constructors
    public TaiKhoan() {}

    public TaiKhoan(String tenDangNhap, String matKhau, String maNV, String quyen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.quyen = quyen;
    }

    // Getters and Setters
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
}

