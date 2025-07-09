/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import DTO.SanPhamDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ApiSanpham {
    
    public static List<SanPhamDTO> layDanhSachSanPham(){
        List<SanPhamDTO> danhSach = new ArrayList<>();
        
        try {
            URL url  = new URL("http://localhost:1234/api/sanpham/getall");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            while((inputLine = reader.readLine()) != null){
                response.append(inputLine);
            }
            
            reader.close();
            
            ObjectMapper mapper = new ObjectMapper();
            danhSach = Arrays.asList(mapper.readValue(response.toString(), SanPhamDTO[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }
    
    public static boolean themSanPham(SanPhamDTO sp){
        return guiYeuCauPOST_PUT(sp, "http://localhost:1234/api/sanpham/create", "POST");
    }
    
    public static boolean suaSanPham(SanPhamDTO sp) {
        return guiYeuCauPOST_PUT(sp, "http://localhost:1234/api/sanpham/update/" + sp.getMaMon(), "PUT");
    }
    
    public static boolean xoaSanPham(String maMon){
        try {
            URL url  = new URL("http://localhost:1234/api/sanpham/delete/" + maMon);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<SanPhamDTO> timSanPham(String maMon){
        List<SanPhamDTO> danhSach = new ArrayList<>();
        try {
            URL url  = new URL("http://localhost:1234/api/sanpham/find/" + maMon);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            while((inputLine = reader.readLine()) != null){
                response.append(inputLine);
            }
            
            reader.close();
            
            ObjectMapper mapper = new ObjectMapper();
            danhSach = Arrays.asList(mapper.readValue(response.toString(), SanPhamDTO[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }
    
    private static boolean guiYeuCauPOST_PUT(SanPhamDTO sp, String urlString, String method) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(sp);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }
            return conn.getResponseCode() == 200 || conn.getResponseCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public static List<SanPhamDTO> timSanPhamGanDung(String keyword) {
            List<SanPhamDTO> danhSach = new ArrayList<>();
            try {
                // ✅ Encode keyword để tránh lỗi tiếng Việt hoặc ký tự đặc biệt
                String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

                // ✅ Tạo URL đúng
                URL url = new URL("http://localhost:1234/api/sanpham/search?keyword=" + encodedKeyword);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                // ✅ Đọc response
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }

                reader.close();

                // ✅ Parse JSON thành List<SanPham>
                ObjectMapper mapper = new ObjectMapper();
                danhSach = Arrays.asList(mapper.readValue(response.toString(), SanPhamDTO[].class));

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return danhSach;
        }


}
