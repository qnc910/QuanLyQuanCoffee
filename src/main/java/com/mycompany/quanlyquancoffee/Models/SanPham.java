package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "san_pham") 
public class SanPham {

    @Id
    @Column(name = "ma_mon", length = 10)
    private String maMon;

    @Column(name = "ten_mon", length = 100)
    private String tenMon;

    @Column(name = "gia")
    private long gia;

    @ManyToOne
    @JoinColumn(name = "ma_loai")
    private LoaiMon loaiMon;

    @Column(name = "hinh_anh", length = 255)
    private String hinhAnh;

    @Column(name = "da_xoa")
    private boolean daXoa = false;

    public SanPham() {
    }

    public SanPham(String maMon, String tenMon, long gia, LoaiMon loaiMon, String hinhAnh) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.gia = gia;
        this.loaiMon = loaiMon;
        this.hinhAnh = hinhAnh;
        this.daXoa = false;
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

    public LoaiMon getLoaiMon() {
        return loaiMon;
    }

    public void setLoaiMon(LoaiMon loaiMon) {
        this.loaiMon = loaiMon;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public String getTenLoai() {
        return loaiMon != null ? loaiMon.getTenLoai() : "";
    }

    public String getMaLoai() {
        return loaiMon != null ? loaiMon.getMaLoai() : "";
    }
}
