package com.mycompany.quanlyquancoffee.Helper;
import DTO.ChiTietMonDTO;
import DTO.HoaDonChiTietDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.math.BigDecimal;

public class PdfExporter {
    public static void xuatHoaDonPdf(HoaDonChiTietDTO hd, String filePath) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Tiêu đề quán
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.ORANGE);
        Paragraph title = new Paragraph("MeowMeow Coffee\n\n", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Thông tin hóa đơn
        document.add(new Paragraph("Mã hóa đơn: " + hd.getMaHd()));
        document.add(new Paragraph("Ngày: " + hd.getNgayLap() + "     Giờ: " + hd.getGio()));
        document.add(new Paragraph("Trạng thái: " + hd.getTrangThai() + "\n\n"));

        // Bảng món ăn
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new float[]{2, 5, 2, 3, 3});
        table.setWidthPercentage(100);
        table.addCell("Mã món");
        table.addCell("Tên món");
        table.addCell("Số lượng");
        table.addCell("Giá bán");
        table.addCell("Thành tiền");

        for (ChiTietMonDTO mon : hd.getMonAn()) {
            table.addCell(mon.getMaMon());
            table.addCell(mon.getTenMon());
            table.addCell(String.valueOf(mon.getSoLuong()));
            table.addCell(mon.getGiaLucBan() + " đ");
            BigDecimal thanhTien = mon.getGiaLucBan().multiply(BigDecimal.valueOf(mon.getSoLuong()));
            table.addCell(thanhTien + " đ");
        }

        document.add(table);

        // Tổng tiền
        document.add(new Paragraph("\nTổng tiền: " + hd.getTongTien() + " đ", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.GREEN)));

        document.close();
    }
}
