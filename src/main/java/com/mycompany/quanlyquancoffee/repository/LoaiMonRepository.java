/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public interface LoaiMonRepository extends JpaRepository<LoaiMon, String> {
    Optional<LoaiMon> findByMaLoai(String maLoai);

    // ✅ Lấy tất cả loại món chưa xoá
    List<LoaiMon> findByDaXoaFalse();

    // ✅ Kiểm tra loại món còn tồn tại chưa xoá mềm
    boolean existsByMaLoaiAndDaXoaFalse(String maLoai);
}

