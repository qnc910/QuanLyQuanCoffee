/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.SanPham;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ADMIN
 */
public interface SanPhamRepository extends JpaRepository<SanPham, String> {
    Optional<SanPham> findByMaMon(String maMon);
}
