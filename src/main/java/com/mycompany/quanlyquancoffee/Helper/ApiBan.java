/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import DTO.BanDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.quanlyquancoffee.Models.KhuVuc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author HELLO
 */
public class ApiBan {
     public static List<KhuVuc> layDanhSachKhuVuc() {
        List<KhuVuc> danhSach = new ArrayList<>();
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
            danhSach = Arrays.asList(mapper.readValue(response.toString(), KhuVuc[].class));
            
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

}
