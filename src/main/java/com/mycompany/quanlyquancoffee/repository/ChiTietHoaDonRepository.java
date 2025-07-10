/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.repository;
import com.mycompany.quanlyquancoffee.Models.ChiTietHoaDon;
import com.mycompany.quanlyquancoffee.Models.ChiTietHoaDonId;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author HELLO
 */
@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, ChiTietHoaDonId> {

    // Tìm tất cả món theo hóa đơn
    List<ChiTietHoaDon> findByHoaDon_MaHd(String maHd);

    // Tổng tiền hóa đơn
    @Query("""
        SELECT SUM(c.soLuong * c.giaLucBan) FROM ChiTietHoaDon c
        WHERE c.hoaDon.maHd = :maHd
    """)
    BigDecimal tinhTongTien(@Param("maHd") String maHd);
    
    
}

