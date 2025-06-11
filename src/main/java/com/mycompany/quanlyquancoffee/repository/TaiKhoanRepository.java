package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.TaiKhoan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    Optional<TaiKhoan> findByTenDangNhapAndMatKhauAndQuyen(String tenDangNhap, String matKhau, String quyen);
}
