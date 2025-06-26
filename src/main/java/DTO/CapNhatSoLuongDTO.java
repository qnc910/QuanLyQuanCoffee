/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import lombok.Data;
/**
 *
 * @author HELLO
 */
@Data
public class CapNhatSoLuongDTO {
    private String maHd;
    private String maMon;
    private int soLuongMoi;

    // Getters và setters
    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public int getSoLuongMoi() {
        return soLuongMoi;
    }

    public void setSoLuongMoi(int soLuongMoi) {
        this.soLuongMoi = soLuongMoi;
    }
}


