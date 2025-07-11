package com.mycompany.quanlyquancoffee.Services;

import com.mycompany.quanlyquancoffee.Models.Ban;
import com.mycompany.quanlyquancoffee.Models.BanHoaDon;
import com.mycompany.quanlyquancoffee.Models.KhuVuc;
import com.mycompany.quanlyquancoffee.repository.BanHoaDonRepository;
import com.mycompany.quanlyquancoffee.repository.BanRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanService {

    @Autowired
    private BanRepository banRepo;

    @Autowired
    private BanHoaDonRepository banHoaDonRepo;

  
    public void xoaBan(String maBan) {
        // Xóa liên kết với hóa đơn (bảng trung gian)
        List<BanHoaDon> banHoaDons = banHoaDonRepo.findByBan_MaBan(maBan);
        banHoaDonRepo.deleteAll(banHoaDons);

        // Đánh dấu bàn đã bị xoá
        Ban ban = banRepo.findById(maBan)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn có mã: " + maBan));

        ban.setDaXoa(true);
        banRepo.save(ban);
    }


    public void khoiPhucBan(String maBan) {
        Ban ban = banRepo.findById(maBan)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn có mã: " + maBan));

        if (!ban.getDaXoa()) {
            throw new RuntimeException("Bàn này chưa bị xoá!");
        }

        ban.setDaXoa(false);
        banRepo.save(ban);
    }

    public void themHoacPhucHoiBan(Ban banMoi) {
        Optional<Ban> optionalBan = banRepo.findById(banMoi.getMaBan());

        if (optionalBan.isPresent()) {
            Ban banCu = optionalBan.get();
            if (Boolean.TRUE.equals(banCu.getDaXoa())) {
                // ✅ Phục hồi và cập nhật lại thông tin bàn cũ
                banCu.setTenBan(banMoi.getTenBan());
                banCu.setTrangThai(banMoi.getTrangThai());
                banCu.setKhuvuc(banMoi.getKhuvuc());
                banCu.setDaXoa(false);
                banRepo.save(banCu);
            } else {
                throw new RuntimeException("❌ Bàn đã tồn tại và chưa bị xoá!");
            }
        } else {
            // ✅ Bàn hoàn toàn mới → thêm mới
            banRepo.save(banMoi);
        }
    }
}
