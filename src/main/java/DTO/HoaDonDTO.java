/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import com.mycompany.quanlyquancoffee.Models.HoaDon;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ADMIN
 */
public class HoaDonDTO {
    private String maHD;
    private String maNV;
    private String tenNV;
    private LocalDate ngayLap;
    private String trangThai;
    private BigDecimal tongTien;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String maHD, String maNV, String tenNV, LocalDate ngayLap, String trangThai, BigDecimal tongTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngayLap = ngayLap;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }
    
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getTenNV() { return tenNV; }
    public void setTenNV(String tenNV) { this.tenNV = tenNV; }

    public LocalDate getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDate ngayLap) { this.ngayLap = ngayLap; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }
}
