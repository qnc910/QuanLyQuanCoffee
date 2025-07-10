/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.LoaiMonDTO;
import com.mycompany.quanlyquancoffee.Models.LoaiMon;
import org.mapstruct.Mapper;

import java.util.List;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author ADMIN
 */
@Mapper(componentModel = "spring")
public interface LoaiMonMapper {
    LoaiMonMapper INSTANCE = Mappers.getMapper(LoaiMonMapper.class);
    
    LoaiMonDTO toDTO(LoaiMon loaiMon);
    LoaiMon toEntity(LoaiMonDTO dto);

    List<LoaiMonDTO> toDTOs(List<LoaiMon> loaiMons);
    List<LoaiMon> toEntities(List<LoaiMonDTO> dtos);
}
