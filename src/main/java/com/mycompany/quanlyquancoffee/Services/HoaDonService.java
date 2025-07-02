package com.mycompany.quanlyquancoffee.Services;

import DTO.*;
import com.mycompany.quanlyquancoffee.Models.*;
import com.mycompany.quanlyquancoffee.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepo;

    @Autowired
    private ChiTietHoaDonRepository chiTietRepo;

    @Autowired
    private BanHoaDonRepository banHoaDonRepo;

    @Autowired
    private BanRepository banRepo;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    // 1️⃣ Tạo hóa đơn mới
    public HoaDonResponse taoHoaDon(TaoHoaDonRequest req) {
        // Kiểm tra hóa đơn chưa thanh toán hôm nay đã tồn tại chưa
        Optional<HoaDon> hdOld = hoaDonRepo.findHoaDonChuaThanhToanByBan(req.getMaBan());
        if (hdOld.isPresent()) {
            throw new RuntimeException("Bàn đã được đặt trước");
        }

        // Sinh mã hóa đơn
        String maHd = "HD" + String.format("%04d", new Random().nextInt(10000));

        HoaDon hd = new HoaDon();
        hd.setMaHd(maHd);
        hd.setNgayLap(LocalDate.now());
        hd.setGio(LocalTime.now());
        hd.setMaNv(req.getMaNv());
        hd.setTrangThai("Chua thanh toan");
        hoaDonRepo.save(hd);

        // Gán bàn
        Ban ban = banRepo.findById(req.getMaBan())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn"));

        BanHoaDon bhd = new BanHoaDon();
        bhd.setHoaDon(hd);
        bhd.setBan(ban);
        banHoaDonRepo.save(bhd);

        // Cập nhật trạng thái bàn
        ban.setTrangThai("Đã đặt");
        banRepo.save(ban);

        return new HoaDonResponse(maHd, List.of(ban.getTenBan()), hd.getNgayLap(), hd.getGio(), hd.getTrangThai());
    }

    // 2️⃣ Thêm món vào hóa đơn
    public void themMonVaoHoaDon(ThemMonRequest req) {
    // 🔒 1. Kiểm tra hóa đơn tồn tại
    HoaDon hd = hoaDonRepo.findById(req.getMaHd())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

    // ❌ 2. Kiểm tra trạng thái hóa đơn
    if (!"Chua thanh toan".equalsIgnoreCase(hd.getTrangThai())) {
        throw new RuntimeException("Không thể thêm món vào hóa đơn đã thanh toán");
    }

    // ✅ 3. Tiếp tục thêm món
    for (ChiTietMonDTO dto : req.getMonAn()) {
        ChiTietHoaDonId id = new ChiTietHoaDonId(req.getMaHd(), dto.getMaMon());
        ChiTietHoaDon ct = chiTietRepo.findById(id).orElse(new ChiTietHoaDon());

        SanPham sp = sanPhamRepo.findById(dto.getMaMon())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy món"));

        ct.setHoaDon(hd);
        ct.setSanPham(sp);
        ct.setSoLuong(dto.getSoLuong());
        ct.setGiaLucBan(dto.getGiaLucBan());

        chiTietRepo.save(ct);
    }
}

    // 3️⃣ Cập nhật số lượng món
    public void capNhatSoLuong(CapNhatSoLuongDTO req) {
        ChiTietHoaDonId id = new ChiTietHoaDonId(req.getMaHd(), req.getMaMon());
        ChiTietHoaDon ct = chiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy món trong hóa đơn"));

        if (req.getSoLuongMoi() <= 0) {
            chiTietRepo.delete(ct);
        } else {
            ct.setSoLuong(req.getSoLuongMoi());
            chiTietRepo.save(ct);
        }

        // Kiểm tra nếu không còn món thì xoá hóa đơn
        List<ChiTietHoaDon> list = chiTietRepo.findByHoaDon_MaHd(req.getMaHd());
        if (list.isEmpty()) {
            hoaDonRepo.deleteById(req.getMaHd());

            List<BanHoaDon> bans = banHoaDonRepo.findByHoaDon_MaHd(req.getMaHd());
            for (BanHoaDon bhd : bans) {
                Ban b = bhd.getBan();
                b.setTrangThai("Trống");
                banRepo.save(b);
            }

            banHoaDonRepo.deleteAll(bans);
        }
    }

    // 4️⃣ Ưu tiên lấy hóa đơn chưa thanh toán, nếu không có thì lấy gần nhất hôm nay
    public Optional<HoaDon> timHoaDonDangSuDung(String maBan) {
        return hoaDonRepo.findHoaDonChuaThanhToanByBan(maBan)
                .or(() -> hoaDonRepo.findHoaDonHomNayByBan(maBan, LocalDate.now()));
    }

    // 5️⃣ Lấy chi tiết hóa đơn (ưu tiên đang sử dụng)
    public HoaDonChiTietDTO layChiTietHoaDonTheoBan(String maBan) {
        HoaDon hd = timHoaDonDangSuDung(maBan)
                .orElseThrow(() -> new RuntimeException("Không có hóa đơn hôm nay cho bàn này"));

        return buildChiTietDTO(maBan, hd);
    }

    // 6️⃣ Lấy hóa đơn hôm nay (không quan tâm trạng thái)
    public HoaDonChiTietDTO layHoaDonHomNayTheoBan(String maBan) {
        HoaDon hd = hoaDonRepo.findHoaDonHomNayByBan(maBan, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Không có hóa đơn hôm nay cho bàn"));

        return buildChiTietDTO(maBan, hd);
    }

    // ✅ Helper: Tạo DTO chi tiết
    private HoaDonChiTietDTO buildChiTietDTO(String maBan, HoaDon hd) {
        List<ChiTietHoaDon> ds = chiTietRepo.findByHoaDon_MaHd(hd.getMaHd());

        List<ChiTietMonDTO> monDTOs = ds.stream().map(ct -> {
            ChiTietMonDTO dto = new ChiTietMonDTO();
            dto.setMaMon(ct.getSanPham().getMaMon());
            dto.setTenMon(ct.getSanPham().getTenMon());
            dto.setSoLuong(ct.getSoLuong());
            dto.setGiaLucBan(ct.getGiaLucBan());
            return dto;
        }).collect(Collectors.toList());

        BigDecimal tongTien = chiTietRepo.tinhTongTien(hd.getMaHd());
        if (tongTien == null) tongTien = BigDecimal.ZERO;

        HoaDonChiTietDTO dto = new HoaDonChiTietDTO();
        dto.setMaHd(hd.getMaHd());
        dto.setMaBan(maBan);
        dto.setNgayLap(hd.getNgayLap().toString());
        dto.setGio(hd.getGio().toString());
        dto.setTrangThai(hd.getTrangThai());
        dto.setMonAn(monDTOs);
        dto.setTongTien(tongTien);

        return dto;
    }
    
    public void thanhToanHoaDon(String maHd) {
    HoaDon hd = hoaDonRepo.findById(maHd)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

    if (!"Chua thanh toan".equalsIgnoreCase(hd.getTrangThai())) {
        throw new RuntimeException("Hóa đơn đã thanh toán rồi");
    }

    // Cập nhật trạng thái hóa đơn
    hd.setTrangThai("Da thanh toan");
    hoaDonRepo.save(hd);
   
}
    public void chuyenMonSangBanKhac(String maHdNguon, String maBanDich, ChiTietMonDTO monChuyen) {
    // Tìm hóa đơn nguồn
        HoaDon hdNguon = hoaDonRepo.findById(maHdNguon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn nguồn"));

        // Tìm/khởi tạo hóa đơn đích
        Optional<HoaDon> hdDichOpt = hoaDonRepo.findHoaDonChuaThanhToanByBan(maBanDich);
        HoaDon hdDich;

        if (hdDichOpt.isPresent()) {
            hdDich = hdDichOpt.get();
        } else {
            // Tạo hóa đơn mới nếu chưa có
            String maHdMoi = "HD" + String.format("%04d", new Random().nextInt(10000));

            hdDich = new HoaDon();
            hdDich.setMaHd(maHdMoi);
            hdDich.setNgayLap(LocalDate.now());
            hdDich.setGio(LocalTime.now());
            hdDich.setTrangThai("Chua thanh toan");
            hoaDonRepo.save(hdDich);

            // Gán bàn vào hóa đơn mới
            Ban ban = banRepo.findById(maBanDich).orElseThrow(() -> new RuntimeException("Không tìm thấy bàn"));
            BanHoaDon bhd = new BanHoaDon();
            bhd.setBan(ban);
            bhd.setHoaDon(hdDich);
            banHoaDonRepo.save(bhd);

            // Cập nhật trạng thái bàn đích
            ban.setTrangThai("Đã đặt");
            banRepo.save(ban);
        }

        // Tìm chi tiết món trong hóa đơn nguồn
        ChiTietHoaDonId idNguon = new ChiTietHoaDonId(maHdNguon, monChuyen.getMaMon());
        ChiTietHoaDon ctNguon = chiTietRepo.findById(idNguon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy món trong hóa đơn nguồn"));

        int soLuongChuyen = monChuyen.getSoLuong();
        int soLuongHienCo = ctNguon.getSoLuong();

        if (soLuongChuyen <= 0 || soLuongChuyen > soLuongHienCo) {
            throw new RuntimeException("Số lượng chuyển không hợp lệ");
        }

        // Cập nhật lại số lượng món ở hóa đơn nguồn
        if (soLuongChuyen == soLuongHienCo) {
            chiTietRepo.delete(ctNguon);
        } else {
            ctNguon.setSoLuong(soLuongHienCo - soLuongChuyen);
            chiTietRepo.save(ctNguon);
        }

        // Thêm/cập nhật món vào hóa đơn đích
        ChiTietHoaDonId idDich = new ChiTietHoaDonId(hdDich.getMaHd(), monChuyen.getMaMon());
        ChiTietHoaDon ctDich = chiTietRepo.findById(idDich).orElse(new ChiTietHoaDon());

        ctDich.setHoaDon(hdDich);
        ctDich.setSanPham(ctNguon.getSanPham());
        ctDich.setGiaLucBan(monChuyen.getGiaLucBan());

        Integer slCuObj = ctDich.getSoLuong();
        int soLuongCu = (slCuObj != null) ? slCuObj : 0;

        ctDich.setSoLuong(soLuongCu + soLuongChuyen);

        chiTietRepo.save(ctDich);

        // Xóa hóa đơn nguồn nếu không còn món
        List<ChiTietHoaDon> conLai = chiTietRepo.findByHoaDon_MaHd(maHdNguon);
        if (conLai.isEmpty()) {
            hoaDonRepo.deleteById(maHdNguon);

            List<BanHoaDon> bhdList = banHoaDonRepo.findByHoaDon_MaHd(maHdNguon);
            for (BanHoaDon bhd : bhdList) {
                Ban b = bhd.getBan();
                b.setTrangThai("Trống");
                banRepo.save(b);
            }

            banHoaDonRepo.deleteAll(bhdList);
        }
    }
    
     public void chuyenBanHoanToan(ChuyenbanRequest req) {
        String maHdCu = req.getMaHdCu();
        String maBanCu = req.getMaBanCu();
        String maBanMoi = req.getMaBanMoi();
        String maNv = req.getMaNv();

        HoaDonChiTietDTO hdCu = layHoaDonHomNayTheoBan(maBanCu);

        Optional<HoaDon> optHdMoi = hoaDonRepo.findHoaDonChuaThanhToanByBan(maBanMoi);
        HoaDon hdMoi;

        if (optHdMoi.isPresent()) {
            hdMoi = optHdMoi.get();
        } else {
            String maHd = "HD" + String.format("%04d", new Random().nextInt(10000));
            hdMoi = new HoaDon();
            hdMoi.setMaHd(maHd);
            hdMoi.setNgayLap(LocalDate.now());
            hdMoi.setGio(LocalTime.now());
            hdMoi.setMaNv(maNv);
            hdMoi.setTrangThai("Chua thanh toan");
            hoaDonRepo.save(hdMoi);

            Ban banMoi = banRepo.findById(maBanMoi).orElseThrow(() -> new RuntimeException("Không tìm thấy bàn mới"));
            BanHoaDon bhd = new BanHoaDon();
            bhd.setHoaDon(hdMoi);
            bhd.setBan(banMoi);
            banHoaDonRepo.save(bhd);

            banMoi.setTrangThai("Đã đặt");
            banRepo.save(banMoi);
        }

        for (ChiTietMonDTO mon : hdCu.getMonAn()) {
            ChiTietHoaDonId id = new ChiTietHoaDonId(hdMoi.getMaHd(), mon.getMaMon());
            ChiTietHoaDon ct = chiTietRepo.findById(id).orElse(new ChiTietHoaDon());

            SanPham sp = sanPhamRepo.findById(mon.getMaMon()).orElseThrow();

            ct.setHoaDon(hdMoi);
            ct.setSanPham(sp);
            ct.setGiaLucBan(mon.getGiaLucBan());
            ct.setSoLuong(mon.getSoLuong());
            chiTietRepo.save(ct);
        }

        for (ChiTietMonDTO mon : hdCu.getMonAn()) {
            CapNhatSoLuongDTO capNhat = new CapNhatSoLuongDTO();
            capNhat.setMaHd(maHdCu);
            capNhat.setMaMon(mon.getMaMon());
            capNhat.setSoLuongMoi(0);
            capNhatSoLuong(capNhat);
        }

        List<BanHoaDon> bans = banHoaDonRepo.findByHoaDon_MaHd(maHdCu);
        for (BanHoaDon bhd : bans) {
            Ban b = bhd.getBan();
            b.setTrangThai("Trống");
            banRepo.save(b);
        }

        banHoaDonRepo.deleteAll(bans);
        hoaDonRepo.deleteById(maHdCu);
    }

}
