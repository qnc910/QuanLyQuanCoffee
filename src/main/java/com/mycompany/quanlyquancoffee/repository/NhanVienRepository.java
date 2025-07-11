package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.NhanVien;
import DTO.NhanVienTaiKhoanDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {

    // ✅ Lấy danh sách nhân viên và tài khoản liên kết, chỉ lấy nhân viên chưa nghỉ
    @Query("SELECT new DTO.NhanVienTaiKhoanDTO(" +
           "nv.maNV, nv.hoTen, nv.cmnd, nv.sdt, nv.diaChi, nv.ngaySinh, nv.ngayVaoLam, nv.viTri, " +
           "tk.tenDangNhap, tk.matKhau, tk.quyen) " +
           "FROM NhanVien nv LEFT JOIN nv.taiKhoan tk " +
           "WHERE nv.daNghi = false")
    List<NhanVienTaiKhoanDTO> getDanhSachNhanVienVaTaiKhoan();

    // ✅ Lấy tất cả nhân viên có tài khoản (fetch join), chỉ lấy người chưa nghỉ
    @Query("SELECT nv FROM NhanVien nv JOIN FETCH nv.taiKhoan WHERE nv.daNghi = false")
    List<NhanVien> findAllNhanVienWithTaiKhoan();

    // ✅ Lấy tất cả nhân viên chưa nghỉ
    List<NhanVien> findByDaNghiFalse();
    
    @Modifying
    @Query("UPDATE NhanVien nv SET nv.daNghi = true WHERE nv.maNV = :maNv")
    void softDeleteNhanVien(@Param("maNv") String maNv);

}
