/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Controllers;

import com.mycompany.quanlyquancoffee.Models.KhuVuc;
import com.mycompany.quanlyquancoffee.repository.KhuVucRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api/khuvuc")
@CrossOrigin(origins = "*")
public class KhuVucController {
    
    @Autowired
    private KhuVucRepository khuVucRepository;
    
    @GetMapping("/getall")
    public List<KhuVuc> getAll() {
        return khuVucRepository.findAll();
    }
    
    
    @GetMapping("/find/{maKV}")
    public Optional<KhuVuc> findKV(@PathVariable String maKV) {
        return khuVucRepository.findById(maKV);
    }
    
    @PostMapping("/create")
    public ResponseEntity<KhuVuc> addKhuVuc(@RequestBody KhuVuc kv){
        if (khuVucRepository.existsById(kv.getMaKV())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // trùng mã
        }
        KhuVuc saved = khuVucRepository.save(kv);
        return ResponseEntity.ok(saved);
    }
    
    @PutMapping("/update/{maKV}")
    public ResponseEntity<KhuVuc> update(@PathVariable String maKV, @RequestBody KhuVuc kv){
        Optional<KhuVuc> kvOpt = khuVucRepository.findById(maKV);
        return khuVucRepository.findById(maKV)
                .map(existing -> {
                    existing.setTenKV(kv.getTenKV());
                    KhuVuc updated = khuVucRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/delete/{maKV}")
    public ResponseEntity<Void> delete(@PathVariable String ma) {
        if (!khuVucRepository.existsById(ma)) {
            return ResponseEntity.notFound().build();
        }
        khuVucRepository.deleteById(ma);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}
