package com.mycompany.quanlyquancoffee.Views;

import DTO.ChiTietMonDTO;
import DTO.HoaDonChiTietDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;

public class XemHoaDonForm extends JDialog {
    private JTable tblMonAn;
    private JLabel lblMaHd, lblTrangThai, lblNgay, lblGio, lblTongTien;

    private final Color cam = new Color(0xFFA726);
    private final Color xanhLa = new Color(0x388E3C);

    public XemHoaDonForm(Frame parent, HoaDonChiTietDTO hd) {
        super(parent, "Chi tiết hóa đơn", true);
        setSize(750, 550);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        Font titleFont = new Font("Segoe UI", Font.BOLD, 22);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        // 🏪 Tiêu đề quán
        JLabel lblTenQuan = new JLabel("MeowMeow Coffee", SwingConstants.CENTER);
        lblTenQuan.setFont(titleFont);
        lblTenQuan.setForeground(cam);
        lblTenQuan.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(lblTenQuan, BorderLayout.NORTH);

        // 🧾 Panel thông tin hóa đơn
        JPanel infoPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        infoPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        infoPanel.setBackground(Color.WHITE);

        lblMaHd = new JLabel("Mã hóa đơn: " + hd.getMaHd());
        lblTrangThai = new JLabel("Trạng thái: " + hd.getTrangThai());
        lblNgay = new JLabel("Ngày: " + hd.getNgayLap());
        lblGio = new JLabel("Giờ: " + hd.getGio());
        lblTongTien = new JLabel("Tổng tiền: " + hd.getTongTien() + " đ");

        for (JLabel lbl : new JLabel[]{lblMaHd, lblTrangThai, lblNgay, lblGio, lblTongTien}) {
            lbl.setFont(textFont);
            lbl.setForeground(xanhLa);
        }

        infoPanel.add(lblMaHd);
        infoPanel.add(lblNgay);
        infoPanel.add(lblGio);
        infoPanel.add(lblTrangThai);
        infoPanel.add(lblTongTien);
        infoPanel.add(new JLabel()); // trống

        add(infoPanel, BorderLayout.NORTH);

        // 🍽️ Bảng món ăn
        String[] columns = {"Mã món", "Tên món", "Số lượng", "Đơn giá (đ)", "Thành tiền (đ)"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (ChiTietMonDTO mon : hd.getMonAn()) {
            BigDecimal thanhTien = mon.getGiaLucBan().multiply(BigDecimal.valueOf(mon.getSoLuong()));
            model.addRow(new Object[]{
                    mon.getMaMon(),
                    mon.getTenMon(),
                    mon.getSoLuong(),
                    mon.getGiaLucBan(),
                    thanhTien
            });
        }

        tblMonAn = new JTable(model);
        tblMonAn.setFont(textFont);
        tblMonAn.setRowHeight(26);
        tblMonAn.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblMonAn.setSelectionBackground(cam);
        tblMonAn.setSelectionForeground(Color.WHITE);
        tblMonAn.setGridColor(new Color(0xBDBDBD));
        tblMonAn.setFillsViewportHeight(true);
        tblMonAn.setShowVerticalLines(false);
        tblMonAn.setShowHorizontalLines(true);

        // Căn phải cho số lượng, đơn giá, thành tiền
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);
        tblMonAn.getColumnModel().getColumn(2).setCellRenderer(rightAlign);
        tblMonAn.getColumnModel().getColumn(3).setCellRenderer(rightAlign);
        tblMonAn.getColumnModel().getColumn(4).setCellRenderer(rightAlign);

        JScrollPane scrollPane = new JScrollPane(tblMonAn);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        add(scrollPane, BorderLayout.CENTER);

        // 🔚 Footer nút đóng
        JButton btnDong = new JButton("Đóng");
        btnDong.setFont(textFont);
        btnDong.setBackground(cam);
        btnDong.setForeground(Color.WHITE);
        btnDong.setFocusPainted(false);
        btnDong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDong.addActionListener(e -> dispose());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(10, 10, 10, 10));
        footer.add(btnDong);
        add(footer, BorderLayout.SOUTH);
    }
}
