package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.SanPham;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanPhamRepository extends JpaRepository<SanPham, String> {

    Optional<SanPham> findByMaMon(String maMon);

    // 🔍 Tìm theo mã món hoặc tên món (không phân biệt hoa thường)
    List<SanPham> findByMaMonContainingIgnoreCaseOrTenMonContainingIgnoreCase(String ma, String ten);

    // ✅ Lấy tất cả sản phẩm chưa xoá mềm
    List<SanPham> findByDaXoaFalse();

    // 🔍 Tìm theo tên hoặc mã nhưng chỉ lọc sản phẩm chưa xoá
    List<SanPham> findByDaXoaFalseAndMaMonContainingIgnoreCaseOrDaXoaFalseAndTenMonContainingIgnoreCase(String ma, String ten);

    // ✅ Lọc sản phẩm theo loại chưa bị xoá
    List<SanPham> findByLoaiMon_MaLoaiAndDaXoaFalse(String maLoai);
}
