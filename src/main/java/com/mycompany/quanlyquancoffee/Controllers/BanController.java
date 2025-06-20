/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Controllers;

import DTO.BanDTO;
import com.mycompany.quanlyquancoffee.Models.Ban;
import com.mycompany.quanlyquancoffee.Models.KhuVuc;
import com.mycompany.quanlyquancoffee.repository.BanRepository;
import com.mycompany.quanlyquancoffee.repository.KhuVucRepository;
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
@RequestMapping("/api/ban")
@CrossOrigin(origins = "*")
public class BanController {
    
    @Autowired
    private BanRepository banRepository;
    
    @Autowired
    private KhuVucRepository khuVucRepository;
    
    
    @GetMapping("/getall")
    public List<BanDTO> getAll(){
        List<Ban> ds = banRepository.findAll();
        List<BanDTO> dsban = new ArrayList<>(); 
        
        for(Ban ban : ds){
            BanDTO dto = new BanDTO();
            dto.setMaBan(ban.getMaBan());
            dto.setTenBan(ban.getTenBan());
            dto.setMaKV(ban.getMaKV());
            dto.setTenKV(ban.getMaBan()!= null ? ban.getTenKV(): "Không rõ");
            dto.setTrangThai(ban.getTrangThai());
            
            dsban.add(dto);
        }
        return dsban;
    }
    
    @GetMapping("/find/{maBan}")
    public Optional<Ban> findBan(@PathVariable String maBan){
        return banRepository.findById(maBan);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> addBan(@RequestBody BanDTO dto){
        Optional<KhuVuc> khuVucOpt = khuVucRepository.findById(dto.getMaKV());
        if(khuVucOpt.isEmpty()) return ResponseEntity.badRequest().body("Khu vực không tồn tại");
        
        Ban ban = new Ban();
        ban.setMaBan(dto.getMaBan());
        ban.setTenBan(dto.getTenBan());
        ban.setTrangThai(dto.getTrangThai());
        ban.setKhuvuc(khuVucOpt.get());

        banRepository.save(ban);
        return ResponseEntity.ok("Đã thêm bàn");
    }
    
    @PutMapping("/update/{maBan}")
    public ResponseEntity<?> update(@PathVariable String maBan, @RequestBody BanDTO dto){
        Optional<Ban> banOpt = banRepository.findById(maBan);
        if (banOpt.isEmpty()) return ResponseEntity.notFound().build();
        
        Optional<KhuVuc> khuVucOpt = khuVucRepository.findById(dto.getMaKV());
        if (khuVucOpt.isEmpty()) return ResponseEntity.badRequest().body("Khu vực không tồn tại");
        
        Ban ban = banOpt.get();
        ban.setTenBan(dto.getTenBan());
        ban.setTrangThai(dto.getTrangThai());
        ban.setKhuvuc(khuVucOpt.get());
        
        banRepository.save(ban);
        return ResponseEntity.ok("Đã cập nhật bàn");
    }
    
    @DeleteMapping("/delete/{maBan}")
    public ResponseEntity<?> delete(@PathVariable String maBan){
        if(!banRepository.existsById(maBan)){
            return ResponseEntity.notFound().build();
        }
        banRepository.deleteById(maBan);
        return ResponseEntity.ok("Đã xoá bàn");
    }
    @GetMapping("/bykhuvuc/{maKV}")
    public List<BanDTO> getBanByKhuVuc(@PathVariable String maKV) {
        List<Ban> ds = banRepository.findByKhuvuc_MaKV(maKV);
        List<BanDTO> dsban = new ArrayList<>();

        for (Ban ban : ds) {
            BanDTO dto = new BanDTO();
            dto.setMaBan(ban.getMaBan());
            dto.setTenBan(ban.getTenBan());
            dto.setMaKV(ban.getMaKV());
            dto.setTenKV(ban.getTenKV());
            dto.setTrangThai(ban.getTrangThai());
            dsban.add(dto);
        }

        return dsban;
    }

}
