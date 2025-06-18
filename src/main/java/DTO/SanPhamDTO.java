/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import com.mycompany.quanlyquancoffee.Models.SanPham;
import com.mycompany.quanlyquancoffee.Models.SanPham;

/**
 *
 * @author ADMIN
 */
public class SanPhamDTO {
    private String maMon;
    private String tenMon;
    private long gia;
    private String tenLoai;
    private String maLoai;
    private String hinhAnh;

    public SanPhamDTO() {
    }
    
    

    public SanPhamDTO(SanPham sp){
        this.maMon = sp.getMaMon();
        this.tenMon = sp.getTenMon();
        this.gia = sp.getGia();
        this.tenLoai = sp.getTenLoai();
        this.maLoai = sp.getMaLoai();
        this.hinhAnh = sp.getHinhAnh();
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
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

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    
}
