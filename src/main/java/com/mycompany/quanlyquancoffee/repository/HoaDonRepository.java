package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.HoaDon;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {

    // 🔹 1. Hóa đơn chưa thanh toán trong hôm nay theo mã bàn (duy nhất)
    @Query("""
        SELECT hd FROM HoaDon hd
        JOIN hd.banHoaDons bhd
        WHERE bhd.ban.maBan = :maBan
          AND hd.trangThai = 'Chua thanh toan'
          AND hd.ngayLap = CURRENT_DATE
    """)
    Optional<HoaDon> findHoaDonChuaThanhToanByBan(@Param("maBan") String maBan);

    // 🔹 2. Hóa đơn gần nhất của bàn (mọi trạng thái)
    @Query("""
        SELECT hd FROM HoaDon hd
        JOIN hd.banHoaDons bhd
        WHERE bhd.ban.maBan = :maBan
        ORDER BY hd.ngayLap DESC, hd.gio DESC
    """)
    List<HoaDon> findHoaDonGanNhatTheoBan(@Param("maBan") String maBan);

    // 🔹 3. Hóa đơn hôm nay gần nhất theo bàn (mọi trạng thái)
    @Query("""
        SELECT hd FROM HoaDon hd
        JOIN hd.banHoaDons bhd
        WHERE bhd.ban.maBan = :maBan
          AND hd.ngayLap = :today
        ORDER BY hd.gio DESC
    """)
    Optional<HoaDon> findHoaDonHomNayByBan(@Param("maBan") String maBan,
                                           @Param("today") LocalDate today);

    // 🔹 4. Danh sách hóa đơn chưa thanh toán trong hôm nay (dùng cho load danh sách các bàn)
    @Query("""
        SELECT hd FROM HoaDon hd
        JOIN hd.banHoaDons bhd
        JOIN bhd.ban b
        WHERE hd.trangThai = 'Chua thanh toan'
          AND b.trangThai <> 'Trống'
          AND hd.ngayLap = CURRENT_DATE
    """)
    List<HoaDon> findHoaDonChuaThanhToanHomNayVaBanDangSuDung();

    // 🔹 5. Hóa đơn theo trạng thái (nếu cần lọc toàn bộ)
    List<HoaDon> findByTrangThai(String trangThai);
}
