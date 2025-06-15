package com.mycompany.quanlyquancoffee.Controllers;

import com.mycompany.quanlyquancoffee.Models.NhanVien;
import com.mycompany.quanlyquancoffee.Models.TaiKhoan;
import com.mycompany.quanlyquancoffee.Models.NhanVienTaiKhoanDTO;
import com.mycompany.quanlyquancoffee.Models.TaiKhoanDTO;
import com.mycompany.quanlyquancoffee.repository.NhanVienRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienRepository nhanVienRepo;

    // ✅ Lấy danh sách nhân viên (thuần)
    @GetMapping("/all")
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepo.findAll();
    }

    // ✅ Lấy danh sách nhân viên kèm tài khoản
    @GetMapping("/with-account")
    public ResponseEntity<List<NhanVienTaiKhoanDTO>> getDanhSachNhanVienVaTaiKhoan() {
        List<NhanVienTaiKhoanDTO> danhSach = nhanVienRepo.getDanhSachNhanVienVaTaiKhoan();
        return ResponseEntity.ok(danhSach);
    }

    // ✅ Thêm nhân viên + tài khoản
    @PostMapping("/add")
    public ResponseEntity<?> createNhanVien(@RequestBody NhanVienTaiKhoanDTO dto) {
        if (nhanVienRepo.existsById(dto.getMaNV())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Mã nhân viên đã tồn tại.");
        }

        NhanVien nv = new NhanVien();
        nv.setMaNV(dto.getMaNV());
        nv.setHoTen(dto.getHoTen());
        nv.setCmnd(dto.getCmnd());
        nv.setSdt(dto.getSdt());
        nv.setDiaChi(dto.getDiaChi());
        nv.setNgaySinh(dto.getNgaySinh());
        nv.setNgayVaoLam(dto.getNgayVaoLam());
        nv.setViTri(dto.getViTri());

        TaiKhoanDTO taiKhoanDTO = dto.getTaiKhoan();
        if (taiKhoanDTO != null) {
            TaiKhoan tk = new TaiKhoan();
            tk.setTenDangNhap(taiKhoanDTO.getTenDangNhap());
            tk.setMatKhau(taiKhoanDTO.getMatKhau());
            tk.setQuyen(taiKhoanDTO.getQuyen());
            tk.setNhanVien(nv); // Quan hệ 2 chiều
            nv.setTaiKhoan(tk);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(nhanVienRepo.save(nv));
    }

    // ✅ Cập nhật nhân viên + tài khoản
    @PutMapping("/{maNV}")
    public ResponseEntity<?> updateNhanVien(@PathVariable String maNV, @RequestBody NhanVienTaiKhoanDTO dto) {
        Optional<NhanVien> optionalNV = nhanVienRepo.findById(maNV);
        if (optionalNV.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên.");
        }

        NhanVien nv = optionalNV.get();

        // Cập nhật thông tin nhân viên
        nv.setHoTen(dto.getHoTen());
        nv.setCmnd(dto.getCmnd());
        nv.setSdt(dto.getSdt());
        nv.setDiaChi(dto.getDiaChi());
        nv.setNgaySinh(dto.getNgaySinh());
        nv.setNgayVaoLam(dto.getNgayVaoLam());
        nv.setViTri(dto.getViTri());

        // Cập nhật tài khoản nếu có
        TaiKhoanDTO taiKhoanDTO = dto.getTaiKhoan();
        if (taiKhoanDTO != null) {
            TaiKhoan tk = nv.getTaiKhoan();
            if (tk == null) {
                tk = new TaiKhoan();
                tk.setNhanVien(nv); // liên kết lại với nhân viên
            }

            tk.setTenDangNhap(taiKhoanDTO.getTenDangNhap());
            tk.setMatKhau(taiKhoanDTO.getMatKhau()); // nếu có mã hóa thì mã hóa ở đây
            tk.setQuyen(taiKhoanDTO.getQuyen());

            nv.setTaiKhoan(tk);
        }

        // Lưu vào DB
        nhanVienRepo.save(nv);

        return ResponseEntity.ok("Cập nhật nhân viên thành công.");
    }

    // ✅ Xoá nhân viên
    @DeleteMapping("/{maNV}")
    @Transactional
    public ResponseEntity<?> deleteNhanVien(@PathVariable String maNV) {
        if (!nhanVienRepo.existsById(maNV)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên.");
        }

        nhanVienRepo.deleteById(maNV);
        return ResponseEntity.ok("Xoá thành công.");
    }
}
