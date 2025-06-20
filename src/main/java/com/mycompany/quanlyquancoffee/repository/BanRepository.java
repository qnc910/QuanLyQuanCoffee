/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.Ban;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository 
public interface BanRepository extends JpaRepository<Ban, String>{
    Optional<Ban> findByMaBan(String maBan);
    List<Ban> findByKhuvuc_MaKV(String maKV);
}

