/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Controllers;

import DTO.ChiTietMonDTO;
import DTO.HoaDonChiTietDTO;
import DTO.HoaDonDTO;
import com.mycompany.quanlyquancoffee.Models.ChiTietHoaDon;
import com.mycompany.quanlyquancoffee.Models.HoaDon;
import com.mycompany.quanlyquancoffee.Services.HoaDonService;
import com.mycompany.quanlyquancoffee.repository.HoaDonRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ADMIN
 */

@RestController
@RequestMapping("/api/thongke")
@CrossOrigin(origins = "*")
public class ThongKeController {
    
    @Autowired
    private HoaDonRepository hoaDonRepository;
    
    @GetMapping("/gethomnay")
    public List<HoaDonDTO> getHDHomNay(){
        return hoaDonRepository.findHoaDonHomNay();
    }
    
    @GetMapping("/find/{maHD}")
    public List<ChiTietMonDTO> findChiTietHoaDon(@PathVariable String maHD){
        return hoaDonRepository.findChiTietMonByMaHd(maHD);
    }
    
    @GetMapping("/getbydate")
    public List<HoaDonDTO> getHDTheoKhoangNgay(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return hoaDonRepository.findHoaDonTheoKhoangThoiGian(startDate, endDate);
    }
}
