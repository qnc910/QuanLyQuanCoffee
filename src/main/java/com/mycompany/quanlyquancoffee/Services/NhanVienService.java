package com.mycompany.quanlyquancoffee.Services;

import com.mycompany.quanlyquancoffee.Models.NhanVien;
import com.mycompany.quanlyquancoffee.repository.NhanVienRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService {


    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Transactional  // cần để JPQL update có tác dụng
    public void xoaNhanVien(String maNv) {
        if (!nhanVienRepository.existsById(maNv)) {
            throw new RuntimeException("Không tìm thấy nhân viên");
        }
        nhanVienRepository.softDeleteNhanVien(maNv);
    }



    /*
      Phục hồi nhân viên đã bị nghỉ (daNghi = true)
     */
    public void khoiPhucNhanVien(String maNv) {
        NhanVien nv = nhanVienRepository.findById(maNv)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên có mã: " + maNv));

        if (!nv.getDaNghi()) {
            throw new RuntimeException("Nhân viên này chưa bị nghỉ!");
        }

        nv.setDaNghi(false);
        nhanVienRepository.save(nv);
    }
}
