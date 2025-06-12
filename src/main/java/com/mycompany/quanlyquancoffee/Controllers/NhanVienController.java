/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Controllers;

import com.mycompany.quanlyquancoffee.Models.NhanVien;
import com.mycompany.quanlyquancoffee.Models.NhanVienTaiKhoanDTO;
import com.mycompany.quanlyquancoffee.repository.NhanVienRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HELLO
 */
@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienRepository nhanVienRepo;

    // Lấy danh sách nhân viên
    @GetMapping
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepo.findAll();
    }
    

    @GetMapping("/api/nhanvien")
    public ResponseEntity<List<NhanVienTaiKhoanDTO>> getDanhSachNhanVienVaTaiKhoan() {
        List<NhanVienTaiKhoanDTO> danhSach = nhanVienRepo.getDanhSachNhanVienVaTaiKhoan();
        return ResponseEntity.ok(danhSach);
    }

    // Thêm nhân viên
    @PostMapping
    public ResponseEntity<?> createNhanVien(@RequestBody NhanVien nv) {
        if (nhanVienRepo.existsById(nv.getMaNV())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Mã nhân viên đã tồn tại.");
        }
        return ResponseEntity.ok(nhanVienRepo.save(nv));
    }

    // Cập nhật nhân viên
    @PutMapping("/{maNV}")
    public ResponseEntity<?> updateNhanVien(@PathVariable String maNV, @RequestBody NhanVien nv) {
        if (!nhanVienRepo.existsById(maNV)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên.");
        }
        nv.setMaNV(maNV);
        return ResponseEntity.ok(nhanVienRepo.save(nv));
    }

    // Xoá nhân viên
    @DeleteMapping("/{maNV}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable String maNV) {
        if (!nhanVienRepo.existsById(maNV)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên.");
        }
        nhanVienRepo.deleteById(maNV);
        return ResponseEntity.ok("Xoá thành công.");
    }

    // Tìm nhân viên theo mã
   @GetMapping("/{maNV}")
    public ResponseEntity<?> getNhanVienById(@PathVariable String maNV) {
        Optional<NhanVien> nv = nhanVienRepo.findById(maNV);
        if (nv.isPresent()) {
            return ResponseEntity.ok(nv.get()); // Trả về NhanVien
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy."); // Trả về String
        }
    }

}

