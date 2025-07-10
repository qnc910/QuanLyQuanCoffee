/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import lombok.*;

/**
 *
 * @author ADMIN
 */

@Data
public class LoaiMonDTO {
    private String maLoai;
    private String tenLoai;

    public LoaiMonDTO() {
    }

    public LoaiMonDTO(LoaiMon lm) {
        this.maLoai = lm.getMaLoai();
        this.tenLoai = lm.getTenLoai();
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    
    @Override
    public String toString(){
        return maLoai + " - " + tenLoai;
    }
}
