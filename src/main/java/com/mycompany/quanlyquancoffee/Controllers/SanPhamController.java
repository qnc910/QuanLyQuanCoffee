/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Controllers;

import com.mycompany.quanlyquancoffee.Models.SanPham;
import com.mycompany.quanlyquancoffee.repository.SanPhamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ADMIN
 */

@RestController
@RequestMapping("/api/sanpham")
public class SanPhamController {
    
    @Autowired
    private SanPhamRepository sanPhamRepository;
    
    /*
    @GetMapping – Đọc dữ liệu

    @PostMapping – Tạo mới

    @PutMapping – Cập nhật

    @DeleteMapping – Xóa
    */
    
    @GetMapping("/getall")
    public List<SanPham> getAll(){
        return sanPhamRepository.findAll();
    }
    
    @PostMapping("/create")
    public SanPham create(@RequestBody SanPham sp){
        return sanPhamRepository.save(sp);
    }
    
    @PutMapping("/update/{maMon}")
    public SanPham update(@PathVariable String maMon, @RequestBody SanPham sp){
        sp.setMaMon(maMon);
        return sanPhamRepository.save(sp);
    }
    
    @DeleteMapping("/delete/{maMon}")
    public void delete(@PathVariable String maMon){
        sanPhamRepository.deleteById(maMon);
    }
}
