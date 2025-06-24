/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.quanlyquancoffee.Models.KhuVuc;
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
public class ApiKhuVuc {
    public static List<KhuVuc> getAllKhuVuc(){
        List<KhuVuc> dskv = new ArrayList<>();
        
        try {
            URL url  = new URL("http://localhost:1234/api/khuvuc/getall");
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
            dskv = Arrays.asList(mapper.readValue(response.toString(), KhuVuc[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dskv;
    }
    
    public static boolean themKhuVuc(KhuVuc kv){
        return guiYeuCauPOST_PUT(kv, "http://localhost:1234/api/khuvuc/create", "POST");
    }
    
    public static boolean suaKhuVuc(KhuVuc kv) {
        return guiYeuCauPOST_PUT(kv, "http://localhost:1234/api/khuvuc/update/" + kv.getMaKV(), "PUT");
    }
    
    public static boolean xoaKhuVuc(String maKV){
        try {
            URL url  = new URL("http://localhost:1234/api/khuvuc/delete/" + maKV);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean guiYeuCauPOST_PUT(KhuVuc kv, String urlString, String method) {
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
    
    public static List<KhuVuc> timKhuVuc(String maKv){
        List<KhuVuc> danhSach = new ArrayList<>();
        try {
            URL url  = new URL("http://localhost:1234/api/khuvuc/find/" + maKv);
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
            danhSach = Arrays.asList(mapper.readValue(response.toString(), KhuVuc[].class));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }
}
