/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author HELLO
 */
@Entity
@Table(name = "ban_hoa_don")
@IdClass(BanHoaDonId.class)
public class BanHoaDon {

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_hd")
    private HoaDon hoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_ban")
    private Ban ban;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }
}
