/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import DTO.ChiTietMonDTO;
import DTO.HoaDonChiTietDTO;
import DTO.HoaDonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.LocalDate;
/**
 *
 * @author ADMIN
 */
public class ApiThongKe {
    public static List<HoaDonDTO> layHoaDonHomNay(){
        List<HoaDonDTO> danhSach = new ArrayList<>();

        try {
            URL url  = new URL("http://localhost:1234/api/hoadon/gethomnay");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();
            conn.disconnect();

            // Cấu hình ObjectMapper để hỗ trợ LocalDate
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // Deserialize JSON thành danh sách
            danhSach = Arrays.asList(mapper.readValue(response.toString(), HoaDonDTO[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }
    
    public static List<ChiTietMonDTO> timHoaDonTheoMa(String maHD){
        List<ChiTietMonDTO> ds = new ArrayList<>();

        try {
            URL url  = new URL("http://localhost:1234/api/hoadon/find/" + maHD);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();
            conn.disconnect();

            // Cấu hình ObjectMapper để hỗ trợ LocalDate
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // Deserialize JSON thành danh sách
            ds = Arrays.asList(mapper.readValue(response.toString(), ChiTietMonDTO[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    
    public static List<HoaDonDTO> layHoaDonTheoKhoangNgay(LocalDate start, LocalDate end) {
    List<HoaDonDTO> danhSach = new ArrayList<>();

    try {
        // Format ngày theo ISO (yyyy-MM-dd)
        String startStr = start.toString();
        String endStr = end.toString();

        // Tạo URL với query params
        URL url = new URL("http://localhost:1234/api/hoadon/getbydate?start=" + startStr + "&end=" + endStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        reader.close();
        conn.disconnect();

        // Cấu hình ObjectMapper để hỗ trợ LocalDate
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Chuyển JSON về danh sách HoaDonDTO
        danhSach = Arrays.asList(mapper.readValue(response.toString(), HoaDonDTO[].class));

    } catch (Exception e) {
        e.printStackTrace();
    }

    return danhSach;
}
}
