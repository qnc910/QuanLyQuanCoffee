/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

/**
 *
 * @author HELLO
 */
public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau; // nên được mã hóa khi lưu
    private String maNV;
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
    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getQuyen() { return quyen; }
    public void setQuyen(String quyen) { this.quyen = quyen; }
}
