package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.NhanVien;
import com.mycompany.quanlyquancoffee.Models.NhanVienTaiKhoanDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {

    // Truy vấn danh sách nhân viên và tài khoản liên kết (LEFT JOIN)
    @Query("SELECT new com.mycompany.quanlyquancoffee.Models.NhanVienTaiKhoanDTO(" +
           "nv.maNV, nv.hoTen, nv.cmnd, nv.sdt, nv.diaChi, nv.ngaySinh, nv.ngayVaoLam, nv.viTri, " +
           "tk.tenDangNhap, tk.quyen) " +
           "FROM NhanVien nv LEFT JOIN nv.taiKhoan tk")
    List<NhanVienTaiKhoanDTO> getDanhSachNhanVienVaTaiKhoan();

    // Truy vấn tất cả nhân viên có liên kết tài khoản
    @Query("SELECT nv FROM NhanVien nv JOIN FETCH nv.taiKhoan") 
    List<NhanVien> findAllNhanVienWithTaiKhoan();
}
