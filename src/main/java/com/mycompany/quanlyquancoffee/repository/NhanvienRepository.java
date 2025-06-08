/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.NhanVien;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author HELLO
 */
public interface NhanvienRepository extends JpaRepository<NhanVien, String> {
    
    // Tìm kiếm theo họ tên (like)
    List<NhanVien> findByHoTenContainingIgnoreCase(String hoTen);

    // Tìm theo vị trí
    List<NhanVien> findByViTri(String viTri);

    // Kiểm tra CMND đã tồn tại chưa
    boolean existsByCmnd(String cmnd);
}
