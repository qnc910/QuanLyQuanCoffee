/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ApiLoaiMon {
    public static List<LoaiMon> layDanhSachLoaiMon(){
        List<LoaiMon> danhSach = new ArrayList<>();
        
        try {
            URL url  = new URL("http://localhost:1234/api/loaimon/getall");
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
            danhSach = Arrays.asList(mapper.readValue(response.toString(), LoaiMon[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }
    
    public static List<LoaiMon> timLoaiMon(String maLoai){
        List<LoaiMon> danhSach = new ArrayList<>();
        try {
            URL url  = new URL("http://localhost:1234/api/loaimon/find/" + maLoai);
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
            danhSach = Arrays.asList(mapper.readValue(response.toString(), LoaiMon.class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return danhSach;
    }
    
    public static boolean themLoaiMon(LoaiMon sp){
        return guiYeuCauPOST_PUT(sp, "http://localhost:1234/api/loaimon/create", "POST");
    }
    
    public static boolean suaLoaiMon(LoaiMon sp) {
        return guiYeuCauPOST_PUT(sp, "http://localhost:1234/api/loaimon/update/" + sp.getMaLoai(), "PUT");
    }
    
    public static boolean xoaLoaiMon(String maMon){
        try {
            URL url  = new URL("http://localhost:1234/api/loaimon/delete/" + maMon);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean guiYeuCauPOST_PUT(LoaiMon lm, String urlString, String method) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(lm);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }
            return conn.getResponseCode() == 200 || conn.getResponseCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
