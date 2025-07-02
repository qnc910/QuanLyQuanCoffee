package com.mycompany.quanlyquancoffee.Controllers;

import DTO.*;
import com.mycompany.quanlyquancoffee.Services.HoaDonService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {

    private final HoaDonService hoaDonService; // ✔ đã inject bằng final và Lombok

    public OrderController(HoaDonService hoaDonService) {
    this.hoaDonService = hoaDonService;
}

    // 1️⃣ Tạo hóa đơn
    @PostMapping("/tao")
    public ResponseEntity<?> taoHoaDon(@RequestBody TaoHoaDonRequest req) {
        try {
            HoaDonResponse res = hoaDonService.taoHoaDon(req);
            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2️⃣ Thêm món vào hóa đơn
    @PostMapping("/them-mon")
    public ResponseEntity<?> themMon(@RequestBody ThemMonRequest req) {
        try {
            hoaDonService.themMonVaoHoaDon(req);
            return ResponseEntity.ok("Đã thêm món vào hóa đơn");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3️⃣ Cập nhật số lượng hoặc hủy món
    @PutMapping("/cap-nhat-so-luong")
    public ResponseEntity<?> capNhatSoLuong(@RequestBody CapNhatSoLuongDTO req) {
        try {
            hoaDonService.capNhatSoLuong(req);
            return ResponseEntity.ok("Cập nhật thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4️⃣ Lấy chi tiết hóa đơn (ưu tiên chưa thanh toán)
    @GetMapping("/chi-tiet/{maBan}")
    public ResponseEntity<?> layChiTietHoaDonTheoBan(@PathVariable String maBan) {
        try {
            HoaDonChiTietDTO dto = hoaDonService.layChiTietHoaDonTheoBan(maBan);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5️⃣ Lấy hóa đơn hôm nay theo mã bàn (mọi trạng thái)
    @GetMapping("/homnay/{maBan}")
    public ResponseEntity<?> layHoaDonHomNayTheoBan(@PathVariable String maBan) {
        try {
            HoaDonChiTietDTO dto = hoaDonService.layHoaDonHomNayTheoBan(maBan);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Không có hóa đơn hôm nay cho bàn này");
        }
    }
    
    // 6️⃣ Thanh toán hóa đơn
    @PutMapping("/thanh-toan/{maHd}")
    public ResponseEntity<?> thanhToanHoaDon(@PathVariable String maHd) {
        try {
            hoaDonService.thanhToanHoaDon(maHd);

            // ✅ Trả về JSON với key "message"
            Map<String, String> res = new HashMap<>();
            res.put("message", "Thanh toán thành công");
            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            // ❌ Trả về lỗi cũng theo định dạng JSON
            Map<String, String> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(err);
        }
    }

  @PostMapping("/chuyen-mon")
    public ResponseEntity<?> chuyenMon(@RequestBody ChuyenMonRequest request) {
        try {
            hoaDonService.chuyenMonSangBanKhac(
                request.getMaHdNguon(),
                request.getMaBanDich(),
                request.getMonChuyen()
            );
            return ResponseEntity.ok("Chuyển món thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }
    
    @PostMapping("/chuyen-ban")
    public ResponseEntity<?> chuyenBan(@RequestBody ChuyenbanRequest req) {
        hoaDonService.chuyenBanHoanToan(req);
        return ResponseEntity.ok(Collections.singletonMap("message", "Chuyển bàn thành công"));
    }


}
