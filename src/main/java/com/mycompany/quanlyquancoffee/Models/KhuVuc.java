package com.mycompany.quanlyquancoffee.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "khu_vuc")
public class KhuVuc {

    @Id
    @Column(name = "ma_kv", length = 10)
    private String maKV;

    @Column(name = "ten_kv", length = 100)
    private String tenKV;

    @Column(name = "da_xoa")
    private Boolean daXoa = false;

    @JsonIgnore
    @OneToMany(mappedBy = "khuvuc", cascade = CascadeType.ALL)
    private List<Ban> danhsachBan;

    public KhuVuc() {}

    public KhuVuc(String maKV, String tenKV) {
        this.maKV = maKV;
        this.tenKV = tenKV;
        this.daXoa = false;
    }

    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getTenKV() {
        return tenKV;
    }

    public void setTenKV(String tenKV) {
        this.tenKV = tenKV;
    }

    public Boolean getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(Boolean daXoa) {
        this.daXoa = daXoa;
    }

    public List<Ban> getDanhsachBan() {
        return danhsachBan;
    }

    public void setDanhsachBan(List<Ban> danhsachBan) {
        this.danhsachBan = danhsachBan;
    }

    @Override
    public String toString() {
        return maKV + " - " + tenKV;
    }
}
