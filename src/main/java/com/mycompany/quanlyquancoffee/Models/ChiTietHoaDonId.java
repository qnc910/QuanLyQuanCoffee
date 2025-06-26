/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;


/**
 *
 * @author HELLO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonId implements Serializable {
    private String hoaDon;
    private String sanPham;

    // 🔧 Constructor mặc định bắt buộc phải có
    public ChiTietHoaDonId() {
    }

    public ChiTietHoaDonId(String hoaDon, String sanPham) {
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }
}
