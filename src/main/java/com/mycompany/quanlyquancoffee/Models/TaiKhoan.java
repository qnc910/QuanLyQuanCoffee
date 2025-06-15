package com.mycompany.quanlyquancoffee.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {

    @Id
    @Column(name = "ten_dang_nhap", length = 50)
    private String tenDangNhap;

    @Column(name = "mat_khau", nullable = false, length = 50)
    private String matKhau;

    @Column(name = "quyen", length = 20)
    private String quyen;

    // Liên kết với NhanVien qua ma_nv
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nv", referencedColumnName = "ma_nv", nullable = true)
    @JsonIgnore
    private NhanVien nhanVien;

    public TaiKhoan() {}

    public TaiKhoan(String tenDangNhap, String matKhau, String quyen, NhanVien nhanVien) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.nhanVien = nhanVien;
    }

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

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @JsonProperty("maNV")
    public String getMaNV() {
        return nhanVien != null ? nhanVien.getMaNV() : null;
    }
}
