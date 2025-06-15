/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyquancoffee.Helper;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.File;
import javax.swing.JLabel;


/**
 *
 * @author ADMIN
 */
public class ImageRenderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel lbl = new JLabel();
        lbl.setOpaque(true);
        
        if (value != null) {
            String imagePath = value.toString();

            // Kiểm tra file tồn tại
            File imgFile = new File(imagePath);
            String fullPath = "../Images/" + imagePath;
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(fullPath);
                Image img = icon.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
                lbl.setIcon(new ImageIcon(img));
            } else {
                lbl.setText("Không tìm thấy hình");
            }
        }
        
        // Highlight dòng được chọn
        if (isSelected) {
            lbl.setBackground(table.getSelectionBackground());
            lbl.setForeground(table.getSelectionForeground());
        } else {
            lbl.setBackground(table.getBackground());
            lbl.setForeground(table.getForeground());
        }

        return lbl;
    }
}
