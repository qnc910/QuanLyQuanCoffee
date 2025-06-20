/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Controllers;

import DTO.SanPhamDTO;
import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import com.mycompany.quanlyquancoffee.Models.SanPham;
import com.mycompany.quanlyquancoffee.repository.LoaiMonRepository;
import com.mycompany.quanlyquancoffee.repository.SanPhamRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ADMIN
 */

@RestController
@RequestMapping("/api/sanpham")
@CrossOrigin(origins = "*")
public class SanPhamController {
    
    @Autowired
    private SanPhamRepository sanPhamRepository;
    
    @Autowired
    private LoaiMonRepository loaiMonRepository;
    
    /*
    @GetMapping – Đọc dữ liệu

    @PostMapping – Tạo mới

    @PutMapping – Cập nhật

    @DeleteMapping – Xóa
    */
    
    @GetMapping("/getall")
    public List<SanPhamDTO> getAll(){
        List<SanPham> ds = sanPhamRepository.findAll();
        List<SanPhamDTO> dssp = new ArrayList<>();
        
        for(SanPham sp : ds){
            SanPhamDTO dto = new SanPhamDTO();
            dto.setMaMon(sp.getMaMon());
            dto.setTenMon(sp.getTenMon());
            dto.setGia(sp.getGia());
            dto.setHinhAnh(sp.getHinhAnh());
            dto.setTenLoai(sp.getMaMon() != null ? sp.getTenLoai() : "Không rõ");
            
            dssp.add(dto);
        }
        return dssp;
    }
    
    @GetMapping("/find/{maMon}")
    public Optional<SanPham> findMon(@PathVariable String maMon){
        return sanPhamRepository.findById(maMon);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> addSanPham(@RequestBody SanPhamDTO dto){
        Optional<LoaiMon> loaiMonOpt = loaiMonRepository.findById(dto.getMaLoai());
        if(loaiMonOpt.isEmpty()) return ResponseEntity.badRequest().body("Loại món không tồn tại");
        
        SanPham sp = new SanPham();
        sp.setMaMon(dto.getMaMon());
        sp.setTenMon(dto.getTenMon());
        sp.setGia(dto.getGia());
        sp.setHinhAnh(dto.getHinhAnh());
        sp.setMaLoai(loaiMonOpt.get());

        sanPhamRepository.save(sp);
        return ResponseEntity.ok("Đã thêm sản phẩm");
    }
    
    @PutMapping("/update/{maMon}")
    public ResponseEntity<?> update(@PathVariable String maMon, @RequestBody SanPhamDTO dto){
        Optional<SanPham> spOpt = sanPhamRepository.findById(maMon);
        if (spOpt.isEmpty()) return ResponseEntity.notFound().build();
        
        Optional<LoaiMon> loaiMonOpt = loaiMonRepository.findById(dto.getMaLoai());
        if (loaiMonOpt.isEmpty()) return ResponseEntity.badRequest().body("Loại món không tồn tại");
        
        SanPham sp = spOpt.get();
        sp.setTenMon(dto.getTenMon());
        sp.setGia(dto.getGia());
        sp.setHinhAnh(dto.getHinhAnh());
        sp.setMaLoai(loaiMonOpt.get());
        
        sanPhamRepository.save(sp);
        return ResponseEntity.ok("Đã cập nhật sản phẩm");
    }
    
    @DeleteMapping("/delete/{maMon}")
    public ResponseEntity<?> delete(@PathVariable String maMon){
        if(!sanPhamRepository.existsById(maMon)){
            return ResponseEntity.notFound().build();
        }
        sanPhamRepository.deleteById(maMon);
        return ResponseEntity.ok("Đã xoá sản phẩm");
    }
    
    @GetMapping("/search")
    public ResponseEntity<?> searchSanPham(@RequestParam("keyword") String keyword) {
        List<SanPham> result = sanPhamRepository.findByMaMonContainingIgnoreCaseOrTenMonContainingIgnoreCase(keyword, keyword);
        return ResponseEntity.ok(result);
    }
}
