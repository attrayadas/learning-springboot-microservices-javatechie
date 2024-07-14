package com.attraya.service;

import com.attraya.model.Order;
import com.attraya.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Generates Excel file
 */
@Service
@AllArgsConstructor
public class ReportService {

    private OrderRepository orderRepository;

    // generate excel file using Apache poi
    public byte[] generateReport() throws IOException {
        List<Order> orders = orderRepository.findAll();
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        writeHeaderLine(sheet);
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Order order : orders) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, order.getId(), style);
            createCell(row, columnCount++, order.getName(), style);
            createCell(row, columnCount++, order.getQty(), style);
            createCell(row, columnCount++, order.getPrice(), style);

        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        return bos.toByteArray();

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }  else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeHeaderLine(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ORDER_ID");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("ORDER_NAME");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("QUANTITY");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("PRICE");

    }
}
