/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import com.mycompany.quanlyquancoffee.repository.LoaiMonRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api/loaimon")
@CrossOrigin(origins = "*")
public class LoaiMonController {
    
    
    @Autowired
    private LoaiMonRepository loaiMonRepository;
    
    @GetMapping("/getall")
    public List<LoaiMon> getAll(){
        System.out.println("LOAIMON GETALL ĐÃ ĐƯỢC GỌI");
        return loaiMonRepository.findAll();
    }
    
    @GetMapping("/gettenloai")
    public List<String> getTenLoai(){
        List<LoaiMon> ds = loaiMonRepository.findAll();
        List<String> tenLoai = new ArrayList<>();
        
        for(LoaiMon lm : ds){
            tenLoai.add(lm.getTenLoai());
        }
        
        return tenLoai;
    }
    
}
