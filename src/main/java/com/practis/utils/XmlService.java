package com.practis.utils;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Optional;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XmlService {

    @Getter private final File file;
    private final XSSFWorkbook workbook;
    private final Sheet sheet;

    /** Create an instance of service to manipulate with xls file. */
    @SneakyThrows
    public XmlService(final String fileName, final String sheetName) {
        file =
                Optional.of(fileName)
                        .map(XmlService.class::getResource)
                        .map(URL::getPath)
                        .map(File::new)
                        .orElseThrow();

        FileInputStream fileInputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
    }

    /** Sets value for column header name. Sets value to next empty cell. */
    public XmlService setUserRow(String firstName, String lastName, String email, String role) {
        set("First Name", firstName);
        set("Last Name", lastName);
        set("Email", email);
        set("Role", role);
        return this;
    }

    /** Sets value for column header name. Sets value to next empty cell. */
    public XmlService set(final String headerValue, final String cellValue) {
        final var header = sheet.getRow(0);
        final var cellIdx = getCellIndex(headerValue, header);
        final var rowIdx = getNextEmptyRow(cellIdx);

        sheet.getRow(rowIdx).getCell(cellIdx).setCellValue(cellValue);
        return this;
    }

    /** Write changes to root xls file. */
    @SneakyThrows
    public void write(final File file) {
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
    }

    private int getNextEmptyRow(final int cellIdx) {
        var continueHeaderSearch = true;
        var rowIdx = 1;
        while (continueHeaderSearch) {
            if (isNull(sheet.getRow(rowIdx).getCell(cellIdx))) {
                continueHeaderSearch = false;
                sheet.getRow(rowIdx).createCell(cellIdx);
            }
            if (isBlank(sheet.getRow(rowIdx).getCell(cellIdx).getStringCellValue())) {
                return rowIdx;
            }
            rowIdx++;
        }
        throw new RuntimeException(
                String.format("Empty cell not found until cell idx %s", cellIdx));
    }

    private int getCellIndex(final String headerValue, final Row header) {
        var continueHeaderSearch = true;
        var cellIdx = 0;
        while (continueHeaderSearch) {
            if (isNull(header.getCell(cellIdx))) {
                continueHeaderSearch = false;
            }
            if (headerValue.equalsIgnoreCase(header.getCell(cellIdx).getStringCellValue())) {
                return cellIdx;
            }
            cellIdx++;
        }
        throw new RuntimeException(String.format("Header %s not found", headerValue));
    }
}
