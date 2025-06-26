package com.mycompany.quanlyquancoffee.Helper;

import DTO.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONArray;

public class ApiHoaDon {
    private static final String BASE_URL = "http://localhost:1234/api/order";

    // Hàm phụ đọc dữ liệu từ response
    private static String docNoiDung(HttpURLConnection conn) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) result.append(line);
            return result.toString();
        }
    }

    // 1️⃣ Lấy chi tiết hóa đơn theo mã bàn
    public static HoaDonChiTietDTO layChiTietHoaDonTheoBan(String maBan) throws IOException {
        URL url = new URL(BASE_URL + "/chi-tiet/" + maBan);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            String json = docNoiDung(conn);
            JSONObject obj = new JSONObject(json);
            
            HoaDonChiTietDTO dto = new HoaDonChiTietDTO();
            dto.setMaHd(obj.getString("maHd"));
            dto.setMaBan(obj.getString("maBan"));
            dto.setNgayLap(obj.getString("ngayLap"));
            dto.setGio(obj.getString("gio"));
            dto.setTrangThai(obj.getString("trangThai"));
            System.out.println("Client nhận trạng thái: " + dto.getTrangThai());
            dto.setTongTien(obj.getBigDecimal("tongTien"));

            JSONArray monAnArr = obj.getJSONArray("monAn");
            for (int i = 0; i < monAnArr.length(); i++) {
                JSONObject item = monAnArr.getJSONObject(i);
                ChiTietMonDTO mon = new ChiTietMonDTO();
                mon.setMaMon(item.getString("maMon"));
                mon.setTenMon(item.getString("tenMon"));
                mon.setSoLuong(item.getInt("soLuong"));
                mon.setGiaLucBan(item.getBigDecimal("giaLucBan"));
                dto.getMonAn().add(mon);
            }

            return dto;
        } else {
            throw new IOException("Không tìm thấy hóa đơn cho bàn " + maBan);
        }
    }

    // 2️⃣ Tạo hóa đơn mới
    public static HoaDonResponse taoHoaDon(TaoHoaDonRequest req) throws IOException {
        URL url = new URL(BASE_URL + "/tao");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Tạo JSON body
        JSONObject body = new JSONObject();
        body.put("maBan", req.getMaBan());
        body.put("maNv", req.getMaNv());

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = body.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            String json = docNoiDung(conn);
            JSONObject obj = new JSONObject(json);

                HoaDonResponse res = new HoaDonResponse();
         res.setMaHoaDon(obj.getString("maHoaDon"));

         List<String> dsBanList = obj.getJSONArray("dsBan")   // đúng với JSON trả về
                             .toList()
                             .stream()
                             .map(Object::toString)
                             .collect(Collectors.toList());
            res.setDsBan(dsBanList);

         res.setNgayLap(LocalDate.parse(obj.getString("ngayLap")));
         res.setGio(LocalTime.parse(obj.getString("gio")));
         res.setTrangThai(obj.getString("trangThai"));
            return res;
        } else {
            throw new IOException("Không tạo được hóa đơn");
        }
    }

    // 3️⃣ Thanh toán hóa đơn
    public static String thanhToan(String maHd) throws IOException {
        URL url = new URL(BASE_URL + "/thanh-toan/" + maHd);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            String json = docNoiDung(conn);
            JSONObject obj = new JSONObject(json);
            return obj.optString("message", "Thành công");
        } else {
            throw new IOException("Không thể thanh toán hóa đơn");
        }
    }
    // ✅ Lấy hóa đơn gần nhất trong hôm nay (không quan tâm trạng thái)
public static HoaDonChiTietDTO layHoaDonHomNayTheoBan(String maBan) throws IOException {
    URL url = new URL(BASE_URL + "/homnay/" + maBan);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    int responseCode = conn.getResponseCode();
    if (responseCode == 200) {
        String json = docNoiDung(conn);
        JSONObject obj = new JSONObject(json);

        HoaDonChiTietDTO dto = new HoaDonChiTietDTO();
        dto.setMaHd(obj.getString("maHd"));
        dto.setMaBan(obj.getString("maBan"));
        dto.setNgayLap(obj.getString("ngayLap"));
        dto.setGio(obj.getString("gio"));
        dto.setTrangThai(obj.getString("trangThai"));
        dto.setTongTien(obj.getBigDecimal("tongTien"));

        JSONArray monAnArr = obj.getJSONArray("monAn");
        for (int i = 0; i < monAnArr.length(); i++) {
            JSONObject item = monAnArr.getJSONObject(i);
            ChiTietMonDTO mon = new ChiTietMonDTO();
            mon.setMaMon(item.getString("maMon"));
            mon.setTenMon(item.getString("tenMon"));
            mon.setSoLuong(item.getInt("soLuong"));
            mon.setGiaLucBan(item.getBigDecimal("giaLucBan"));
            dto.getMonAn().add(mon);
        }

        return dto;
    } else {
        throw new IOException("Không có hóa đơn hôm nay cho bàn");
    }
}

// 4️⃣ Thêm món vào hóa đơn
public static void themMonVaoHoaDon(ThemMonRequest req) throws IOException {
    URL url = new URL(BASE_URL + "/them-mon");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);

    // Tạo JSON body
    JSONObject body = new JSONObject();
    body.put("maHd", req.getMaHd());

    JSONArray dsMon = new JSONArray();
    for (ChiTietMonDTO mon : req.getMonAn()) {
        JSONObject monObj = new JSONObject();
        monObj.put("maMon", mon.getMaMon());
        monObj.put("soLuong", mon.getSoLuong());
        monObj.put("giaLucBan", mon.getGiaLucBan());
        dsMon.put(monObj);
    }

    body.put("monAn", dsMon);

    try (OutputStream os = conn.getOutputStream()) {
        byte[] input = body.toString().getBytes(StandardCharsets.UTF_8);
        os.write(input);
    }

    int responseCode = conn.getResponseCode();
    if (responseCode != 200) {
        throw new IOException("Thêm món thất bại. Mã hóa đơn: " + req.getMaHd());
    }
}

    
}
