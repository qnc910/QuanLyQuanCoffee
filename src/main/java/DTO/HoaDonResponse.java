package DTO;

import com.mycompany.quanlyquancoffee.Models.HoaDon;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class HoaDonResponse {
    private String maHoaDon;
    private LocalDate ngayLap;
    private LocalTime gio;
    private String trangThai;
    private List<String> dsBan;

    
 

public HoaDonResponse(String maHoaDon, List<String> dsBan, LocalDate ngayLap, LocalTime gio, String trangThai) {
    this.maHoaDon = maHoaDon;
    this.dsBan = dsBan;
    this.ngayLap = ngayLap;
    this.gio = gio;
    this.trangThai = trangThai;
}

    public HoaDonResponse() {
        
    }

    // Getters và setters (hoặc dùng Lombok nếu thích)

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public LocalTime getGio() {
        return gio;
    }

    public void setGio(LocalTime gio) {
        this.gio = gio;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<String> getDsBan() {
        return dsBan;
    }
    
    public String getTenBan() {
    return (dsBan != null && !dsBan.isEmpty()) ? dsBan.get(0) : "";
} 
    public void setDsBan(List<String> dsBan) {
        this.dsBan = dsBan;
    }
}
