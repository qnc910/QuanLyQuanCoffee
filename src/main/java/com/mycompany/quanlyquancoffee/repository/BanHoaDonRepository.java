/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.BanHoaDon;
import com.mycompany.quanlyquancoffee.Models.BanHoaDonId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HELLO
 */
@Repository
public interface BanHoaDonRepository extends JpaRepository<BanHoaDon, BanHoaDonId> {

    // Tìm theo mã hóa đơn
    List<BanHoaDon> findByHoaDon_MaHd(String maHd);
    List<BanHoaDon> findByBan_MaBan(String maBan);

    // Tìm hóa đơn đang chiếm dụng bàn
    @Query("SELECT bhd FROM BanHoaDon bhd WHERE bhd.ban.maBan = :maBan AND bhd.hoaDon.trangThai = 'Chua thanh toan'")
    List<BanHoaDon> findHoaDonChuaThanhToanByBan(@Param("maBan") String maBan);
    
    @Modifying
    @Query("DELETE FROM BanHoaDon bhd WHERE bhd.hoaDon.maHd = :maHd AND bhd.ban.maBan = :maBan")
    void deleteByHoaDonAndBan(@Param("maHd") String maHd, @Param("maBan") String maBan);

}

