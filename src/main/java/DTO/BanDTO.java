/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;



/**
 *
 * @author ADMIN
 */
public class BanDTO {
    private String maBan;
    private String tenBan;
    private String maKV;
    private String tenKV;
    private String trangThai;

    public BanDTO() {
    }

   
    
       public BanDTO(String maBan, String tenBan, String maKhuVuc, String trangThai) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.maKV = maKhuVuc;
        this.trangThai = trangThai;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
