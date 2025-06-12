/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

/**
 *
 * @author HELLO
 */
//Lớp hỗ trợ lưu phiên đăng nhập cho phần đăng nhập để phân quyền
public class UserSession {
    private static String username;
    private static String quyen;
    private static String maNV;

    public static void setUsername(String username) {
        UserSession.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static void setQuyen(String quyen) {
        UserSession.quyen = quyen;
    }

    public static String getQuyen() {
        return quyen;
    }

    public static void setMaNV(String maNV) {
        UserSession.maNV = maNV;
    }

    public static String getMaNV() {
        return maNV;
    }

    public static void clearSession() {
        username = null;
        quyen = null;
        maNV = null;
    }

}



