package com.tmasolutions.factory.excel;


import com.tmasolutions.model.Book;
import com.tmasolutions.repo.BookRepository;
import com.tmasolutions.utils.ExcelHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class BookExcelService {
    @Autowired
    BookRepository repository;
    public void importBookFromExcel(MultipartFile file) {
        try {
            List<Book> books = ExcelHelper.excelToBooks(file.getInputStream());
            repository.saveAll(books);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public void exportAllBook(HttpServletResponse response) throws IOException {
        workbook = new XSSFWorkbook();
        listExportBooks = repository.findAll();
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Book> listExportBooks;
    private void writeHeaderLine() {
        sheet = workbook.createSheet("All Books");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Book ID", style);
        createCell(row, 1, "Full Name", style);
        createCell(row, 2, "Description", style);
        createCell(row, 3, "Author", style);
        createCell(row, 4, "Language", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Book user : listExportBooks) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, user.getId(), style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getDescription(), style);
            createCell(row, columnCount++, user.getAuthor().toString(), style);
            createCell(row, columnCount++, user.getLanguage(), style);

        }
    }
}
