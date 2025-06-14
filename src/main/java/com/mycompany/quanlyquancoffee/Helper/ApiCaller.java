/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.quanlyquancoffee.Models.SanPham;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    public static List<SanPham> layDanhSachSanPham(){
        List<SanPham> danhSach = new ArrayList<>();
        
        try {
            URL url  = new URL("http://localhost:1234/api/sanpham/getall");
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
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }
}
