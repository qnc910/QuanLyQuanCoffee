/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author HELLO
 */

    @Data
public class ChiTietMonDTO {
    private String maMon;
    private String tenMon;
    private int soLuong;
    private BigDecimal giaLucBan;
    private BigDecimal thanhTien;

    public ChiTietMonDTO() {
    }

    public ChiTietMonDTO(String maMon, String tenMon, int soLuong, BigDecimal giaLucBan, BigDecimal thanhTien) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.giaLucBan = giaLucBan;
        this.thanhTien = thanhTien;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaLucBan() {
        return giaLucBan;
    }

    public void setGiaLucBan(BigDecimal giaLucBan) {
        this.giaLucBan = giaLucBan;
    }
       public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
}


