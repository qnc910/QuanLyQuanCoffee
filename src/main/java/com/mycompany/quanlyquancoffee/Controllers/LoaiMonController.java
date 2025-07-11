package com.mycompany.quanlyquancoffee.Controllers;

import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import com.mycompany.quanlyquancoffee.repository.LoaiMonRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loaimon")
@CrossOrigin(origins = "*")
public class LoaiMonController {

    @Autowired
    private LoaiMonRepository loaiMonRepository;

    // ✅ Chỉ lấy loại món chưa xoá
    @GetMapping("/getall")
    public List<LoaiMon> getAll() {
        return loaiMonRepository.findByDaXoaFalse();
    }

    @GetMapping("/find/{maLoai}")
    public Optional<LoaiMon> findMon(@PathVariable String maLoai) {
        return loaiMonRepository.findByMaLoai(maLoai);
    }

    @PostMapping("/create")
    public ResponseEntity<LoaiMon> addLoaiMon(@RequestBody LoaiMon lm) {
        if (loaiMonRepository.existsById(lm.getMaLoai())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Trùng mã
        }
        lm.setDaXoa(false); // Mặc định chưa bị xoá
        LoaiMon saved = loaiMonRepository.save(lm);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{maLoai}")
    public ResponseEntity<LoaiMon> update(@PathVariable String maLoai, @RequestBody LoaiMon lm) {
        return loaiMonRepository.findById(maLoai)
                .map(existing -> {
                    existing.setTenLoai(lm.getTenLoai());
                    LoaiMon updated = loaiMonRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Xoá mềm
    @DeleteMapping("/delete/{maLoai}")
    public ResponseEntity<?> delete(@PathVariable String maLoai) {
        Optional<LoaiMon> loaiOpt = loaiMonRepository.findById(maLoai);
        if (loaiOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LoaiMon loai = loaiOpt.get();
        loai.setDaXoa(true);
        loaiMonRepository.save(loai);
        return ResponseEntity.ok("✅ Đã xoá mềm loại món: " + maLoai);
    }
}
