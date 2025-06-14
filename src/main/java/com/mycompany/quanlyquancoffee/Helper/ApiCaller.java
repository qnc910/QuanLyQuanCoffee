/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import DTO.SanPhamDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.quanlyquancoffee.Models.SanPham;
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
public class ApiCaller {
    
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
    
    public static boolean themSanPham(SanPham sp){
        return guiYeuCauPOST_PUT(sp, "http://localhost:1234/api/sanpham/create", "POST");
    }
    
    public static boolean suaSanPham(SanPham sp) {
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
    
    public static List<SanPham> timSanPham(String maMon){
        List<SanPham> danhSach = new ArrayList<>();
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
            danhSach = Arrays.asList(mapper.readValue(response.toString(), SanPham[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }
    
    private static boolean guiYeuCauPOST_PUT(SanPham sp, String urlString, String method) {
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
}
