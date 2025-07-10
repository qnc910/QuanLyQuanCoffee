/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import com.mycompany.quanlyquancoffee.Models.KhuVuc;

/**
 *
 * @author ADMIN
 */
public class KhuVucDTO {
    private String maKv;
    private String tenKv;

    public KhuVucDTO() {
    }

    public KhuVucDTO(KhuVuc kv) {
        this.maKv = kv.getMaKV();
        this.tenKv = kv.getTenKV();
    }

    public String getMaKv() {
        return maKv;
    }

    public void setMaKv(String maKv) {
        this.maKv = maKv;
    }

    public String getTenKv() {
        return tenKv;
    }

    public void setTenKv(String tenKv) {
        this.tenKv = tenKv;
    }
    
    
}
