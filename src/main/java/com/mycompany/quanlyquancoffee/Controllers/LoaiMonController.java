/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.quanlyquancoffee.Controllers;
import DTO.LoaiMonDTO;
import Mapper.LoaiMonMapper;
import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import com.mycompany.quanlyquancoffee.repository.LoaiMonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api/loaimon")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoaiMonController {
    
    
    @Autowired
    private LoaiMonRepository loaiMonRepository;
    
    private LoaiMonMapper loaiMonMapper;
    
    @GetMapping("/getall")
    public List<LoaiMon> getAll() {
        return loaiMonRepository.findAll();
    }
    
    @GetMapping("/find/{maMon}")
    public Optional<LoaiMon> findMon(@PathVariable String maMon) {
        return loaiMonRepository.findById(maMon);
    }
    
    @PostMapping("/create")
    public ResponseEntity<LoaiMon> addLoaiMon(@RequestBody LoaiMon lm){
        if (loaiMonRepository.existsById(lm.getMaLoai())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // trùng mã
        }
        LoaiMon saved = loaiMonRepository.save(lm);
        return ResponseEntity.ok(saved);
    }
    
    @PutMapping("/update/{maLoai}")
    public ResponseEntity<LoaiMon> update(@PathVariable String maLoai, @RequestBody LoaiMon lm){
        Optional<LoaiMon> lmOpt = loaiMonRepository.findById(maLoai);
        return loaiMonRepository.findById(maLoai)
                .map(existing -> {
                    existing.setTenLoai(lm.getTenLoai());
                    LoaiMon updated = loaiMonRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<Void> delete(@PathVariable String ma) {
        if (!loaiMonRepository.existsById(ma)) {
            return ResponseEntity.notFound().build();
        }
        loaiMonRepository.deleteById(ma);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}
