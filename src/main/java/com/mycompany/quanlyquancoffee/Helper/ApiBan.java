/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import DTO.BanDTO;
import DTO.KhuVucDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author HELLO
 */
public class ApiBan {
     public static List<KhuVucDTO> layDanhSachKhuVuc() {
        List<KhuVucDTO> danhSach = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:1234/api/khuvuc/getall");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            StringBuilder response = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            reader.close();
            
            ObjectMapper mapper = new ObjectMapper();
            danhSach = Arrays.asList(mapper.readValue(response.toString(), KhuVucDTO[].class));
            
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }
     
       public static List<BanDTO> layDanhSachBanTheoKhuVuc(String maKV) {
       List<BanDTO> danhSachBan = new ArrayList<>();
       try {
           URL url = new URL("http://localhost:1234/api/ban/bykhuvuc/" + maKV);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");

           if (conn.getResponseCode() == 200) {
               BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
               StringBuilder response = new StringBuilder();
               String line;

               while ((line = reader.readLine()) != null) {
                   response.append(line);
               }
               reader.close();

               ObjectMapper mapper = new ObjectMapper();
               // Khi server trả về List<BanDTO>
               danhSachBan = Arrays.asList(mapper.readValue(response.toString(), BanDTO[].class));
           } else {
               System.err.println("Lỗi khi lấy danh sách bàn. Mã lỗi: " + conn.getResponseCode());
           }

           conn.disconnect();
       } catch (Exception e) {
           e.printStackTrace();
       }

       return danhSachBan;
   }

    public static List<BanDTO> getAllBan(){
        List<BanDTO> dsBan = new ArrayList<>();
        
        try {
            URL url  = new URL("http://localhost:1234/api/ban/getall");
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
            dsBan = Arrays.asList(mapper.readValue(response.toString(), BanDTO[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dsBan;
    }
    
    public static boolean themBan(BanDTO kv){
        return guiYeuCauPOST_PUT(kv, "http://localhost:1234/api/ban/create", "POST");
    }
    
    public static boolean suaBan(BanDTO kv) {
        return guiYeuCauPOST_PUT(kv, "http://localhost:1234/api/ban/update/" + kv.getMaBan(), "PUT");
    }
    
    public static boolean xoaBan(String maKV){
        try {
            URL url  = new URL("http://localhost:1234/api/ban/delete/" + maKV);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean guiYeuCauPOST_PUT(BanDTO kv, String urlString, String method) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(kv);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }
            return conn.getResponseCode() == 200 || conn.getResponseCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<BanDTO> timBan(String maBan){
        List<BanDTO> danhSach = new ArrayList<>();
        try {
            URL url  = new URL("http://localhost:1234/api/ban/find/" + maBan);
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
            danhSach = Arrays.asList(mapper.readValue(response.toString(), BanDTO[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }
    
        public static boolean capNhatTrangThaiBan(String maBan, String trangThai) throws IOException {
        URL url = new URL("http://localhost:1234/api/ban/dat-ban/" + maBan);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JSONObject body = new JSONObject();
        body.put("trangThai", trangThai);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.toString().getBytes(StandardCharsets.UTF_8));
        }

        return conn.getResponseCode() == 200;
    }

}
