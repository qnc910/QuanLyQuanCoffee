package com.mycompany.quanlyquancoffee.Controllers;

import com.mycompany.quanlyquancoffee.Models.KhuVuc;
import com.mycompany.quanlyquancoffee.repository.BanRepository;
import com.mycompany.quanlyquancoffee.repository.KhuVucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/khuvuc")
@CrossOrigin(origins = "*")
public class KhuVucController {

    @Autowired
    private KhuVucRepository khuVucRepository;

    @Autowired
    private BanRepository banRepository;

    // ✅ Lấy tất cả khu vực
    @GetMapping("/getall")
    public List<KhuVuc> getAll() {
         return khuVucRepository.findByDaXoaFalse();
    }

    // ✅ Tìm theo mã KV
    @GetMapping("/find/{maKV}")
    public Optional<KhuVuc> findKV(@PathVariable String maKV) {
        return khuVucRepository.findById(maKV);
    }
    

    // ✅ Tạo khu vực mới
    @PostMapping("/create")
    public ResponseEntity<?> addKhuVuc(@RequestBody KhuVuc kv) {
        if (khuVucRepository.existsById(kv.getMaKV())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("❌ Mã khu vực đã tồn tại.");
        }
        KhuVuc saved = khuVucRepository.save(kv);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ✅ Cập nhật khu vực
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

    // ✅ Xoá khu vực nếu không còn bàn hoạt động (daXoa = false)
    @DeleteMapping("/delete/{maKV}")
    public ResponseEntity<String> delete(@PathVariable String maKV) {
        if (!khuVucRepository.existsById(maKV)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("⚠️ Không tìm thấy khu vực có mã: " + maKV);
        }

        boolean conBanChuaXoa = banRepository.existsByKhuvuc_MaKVAndDaXoaFalse(maKV);
        if (conBanChuaXoa) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("❌ Khu vực này vẫn còn bàn hoạt động. Vui lòng xoá mềm tất cả bàn trước khi xoá khu vực.");
        }

        KhuVuc kv = khuVucRepository.findById(maKV).get();
        kv.setDaXoa(true);
        khuVucRepository.save(kv);
        return ResponseEntity.ok("✅ Đã xoá khu vực: " + maKV);
    }
}
