package com.mycompany.quanlyquancoffee.Controllers;

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
    public ResponseEntity<String> dangNhap(@RequestBody TaiKhoan tk) {
        System.out.println("Yêu cầu đăng nhập: " + tk.getTenDangNhap() + ", " + tk.getMatKhau() + ", " + tk.getQuyen());

        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findByTenDangNhapAndMatKhauAndQuyen(
            tk.getTenDangNhap(), tk.getMatKhau(), tk.getQuyen()
        );

        if (taiKhoan.isPresent()) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
        }
    }
}
