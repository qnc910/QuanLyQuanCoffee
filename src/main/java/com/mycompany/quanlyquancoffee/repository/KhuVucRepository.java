package com.mycompany.quanlyquancoffee.repository;

import com.mycompany.quanlyquancoffee.Models.KhuVuc;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhuVucRepository extends JpaRepository<KhuVuc, String> {

    Optional<KhuVuc> findByMaKV(String maKV);

    // ✅ Lấy danh sách khu vực chưa bị xoá mềm
    List<KhuVuc> findByDaXoaFalse();

    // ✅ Kiểm tra nếu khu vực còn bàn chưa xoá mềm
    boolean existsByMaKVAndDanhsachBan_DaXoaFalse(String maKV);
}
