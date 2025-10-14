/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "khu_vuc") 
public class KhuVuc {
    @Id 
    @Column(name = "ma_kv", length = 10)
    private String maKV;
    
    @Column(name = "ten_kv", length = 100)
    private String tenKV;
    
    @JsonIgnore
    @OneToMany(mappedBy = "khuvuc", cascade = CascadeType.ALL)
    private List<Ban> danhsachBan;

    public KhuVuc() {
    }

    public KhuVuc(String maKV, String tenKV) {
        this.maKV = maKV;
        this.tenKV = tenKV;
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
     @Override
    public String toString() {
        return maKV + " - " + tenKV;
    }
    
}
