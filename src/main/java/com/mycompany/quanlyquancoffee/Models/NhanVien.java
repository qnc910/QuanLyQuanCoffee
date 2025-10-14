package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nhan_vien")  // Tên bảng trùng SQL
public class NhanVien {

    @Id
    @Column(name = "ma_nv", length = 10)
    private String maNV;

    @Column(name = "ho_ten", length = 100, nullable = false)
    private String hoTen;

    @Column(name = "cmnd", length = 20)
    private String cmnd;

    @Column(name = "sdt", length = 15)
    private String sdt;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "ngay_sinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;

    @Column(name = "ngay_vao_lam")
    @Temporal(TemporalType.DATE)
    private Date ngayVaoLam;

    @Column(name = "vi_tri", length = 50)
    private String viTri;

    // Quan hệ 1-1 với TaiKhoan, mappedBy phải đúng với tên field ở TaiKhoan
    @OneToOne(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private TaiKhoan taiKhoan;

    // Constructors
    public NhanVien() {}

    public NhanVien(String maNV, String hoTen, String cmnd, String sdt, String diaChi, Date ngaySinh, Date ngayVaoLam, String viTri) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.viTri = viTri;
    }

    // Getters and Setters
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
