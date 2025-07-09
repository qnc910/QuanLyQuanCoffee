/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author HELLO
 */
import java.util.List;
import lombok.Data;
@Data
public class ThemMonRequest {
    private String maHd;

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public List<ChiTietMonDTO> getMonAn() {
        return monAn;
    }

    public void setMonAn(List<ChiTietMonDTO> monAn) {
        this.monAn = monAn;
    }
    private List<ChiTietMonDTO> monAn;
}