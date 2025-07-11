package com.mycompany.quanlyquancoffee.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "loai_mon")
public class LoaiMon {

    @Id
    @Column(name = "ma_loai", length = 10)
    private String maLoai;

    @Column(name = "ten_loai", length = 100)
    private String tenLoai;

    @Column(name = "da_xoa")
    private boolean daXoa = false;

    @JsonIgnore
    @OneToMany(mappedBy = "loaiMon", cascade = CascadeType.ALL)
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

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public List<SanPham> getDanhsachSP() {
        return danhsachSP;
    }

    public void setDanhsachSP(List<SanPham> danhsachSP) {
        this.danhsachSP = danhsachSP;
    }

    @Override
    public String toString() {
        return maLoai + " - " + tenLoai;
    }
}
