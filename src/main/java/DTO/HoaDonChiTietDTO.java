/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
/**
 *
 * @author HELLO
 */
@Data
public class HoaDonChiTietDTO {
    private String maHd;
    private String maBan;
    private String ngayLap;
    private String gio;

    
    public List<ChiTietMonDTO> getMonAn() {
    if (monAn == null) {
        monAn = new ArrayList<>();
    }
    return monAn;
}


    
    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    /* public List<ChiTietMonDTO> getMonAn() {
    return monAn;
    }*/

    public void setMonAn(List<ChiTietMonDTO> monAn) {
        this.monAn = monAn;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
    private String trangThai;
    private List<ChiTietMonDTO> monAn;
    private BigDecimal tongTien;

}
