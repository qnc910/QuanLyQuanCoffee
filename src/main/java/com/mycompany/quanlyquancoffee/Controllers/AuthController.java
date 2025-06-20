package com.mycompany.quanlyquancoffee.Controllers;

import DTO.DoiMatKhauDTO;
import com.mycompany.quanlyquancoffee.Models.TaiKhoan;
import com.mycompany.quanlyquancoffee.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @PostMapping("/api/taikhoan/dangnhap")
    public ResponseEntity<?> dangNhap(@RequestBody TaiKhoan tk) {
        System.out.println("Yêu cầu đăng nhập: " + tk.getTenDangNhap() + ", " + tk.getMatKhau() + ", " + tk.getQuyen());

        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findByTenDangNhapAndMatKhauAndQuyen(
            tk.getTenDangNhap(), tk.getMatKhau(), tk.getQuyen()
        );

        if (taiKhoan.isPresent()) {
            TaiKhoan found = taiKhoan.get();

            String json = String.format(
                "{" +
                    "\"message\":\"Đăng nhập thành công\"," +
                    "\"tenDangNhap\":\"%s\"," +
                    "\"quyen\":\"%s\"," +
                    "\"maNV\":\"%s\"" +
                "}",
                found.getTenDangNhap(),
                found.getQuyen(),
                found.getMaNV() != null ? found.getMaNV() : "" // tránh null
            );

            return ResponseEntity.ok(json);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Sai thông tin đăng nhập");
        }
    }
    

    @PutMapping("api/taikhoan/doimatkhau")
    public ResponseEntity<?> doiMatKhau(@RequestBody DoiMatKhauDTO dto) {
        Optional<TaiKhoan> optional = taiKhoanRepository.findById(dto.getTenDangNhap());

        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tài khoản không tồn tại.");
        }

        TaiKhoan tk = optional.get();

        if (!tk.getMatKhau().equals(dto.getMatKhauCu())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu cũ không đúng.");
        }

        if (tk.getMatKhau().equals(dto.getMatKhauMoi())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu mới không được trùng với mật khẩu cũ.");
        }

        tk.setMatKhau(dto.getMatKhauMoi()); // Có thể mã hóa ở đây nếu cần
        taiKhoanRepository.save(tk);

        return ResponseEntity.ok("Đổi mật khẩu thành công.");
    }
}


