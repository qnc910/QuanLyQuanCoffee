package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.Ban;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface BanRepository extends JpaRepository<Ban, String> {

    // ✅ Tìm bàn theo mã (cả bàn đã xoá và chưa xoá)
    Optional<Ban> findByMaBan(String maBan);

    // ✅ Tìm tất cả bàn thuộc khu vực, không bị xoá
    List<Ban> findByKhuvuc_MaKVAndDaXoaFalse(String maKV);

    // ✅ Tìm tất cả bàn chưa bị xoá
    List<Ban> findByDaXoaFalse();
    
    boolean existsByKhuvuc_MaKVAndDaXoaFalse(String maKV);

}
