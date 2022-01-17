package io.quarkiverse.poi.it;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;

/**
 *
 */
@ApplicationScoped
public class PoiService {

    private static final Logger LOGGER = Logger.getLogger(PoiService.class);

    public ByteArrayInputStream exportExcel() {
        String[] columns = { "One", "Two", "Three", "Four", "Five" };

        // Create a Workbook
        // new HSSFWorkbook() for generating `.xls` file
        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream fileOut = new ByteArrayOutputStream();) {
            /*
             * CreationHelper helps us create instances of various things like DataFormat,
             * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
             */
            CreationHelper createHelper = workbook.getCreationHelper();

            // Create a Sheet
            Sheet sheet = workbook.createSheet("Employee");

            // Create a Font for styling header cells
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            short fontHeight = 18;
            headerFont.setFontHeightInPoints(fontHeight);
            headerFont.setColor(IndexedColors.RED.getIndex());

            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            // Create cells

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create Cell Style for formatting Date
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            // Create Other rows and cells with employees data
            // Resize all columns to fit the content size
            //        for (int i=0; i<columns.length; i++) {
            //            sheet.autoSizeColumn(i);
            //        }

            // Write the output to a file
            workbook.write(fileOut);
            return new ByteArrayInputStream(fileOut.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Report generation issue");
        }
    }

    public void readExcel(Path path) {
        try (
                InputStream in = Files.newInputStream(path);
                Workbook workbook = WorkbookFactory.create(in)) {
            Sheet sheet = workbook.getSheetAt(0);

            DataFormatter formatter = new DataFormatter();
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            LOGGER.infof("FirstRowNum: %s, LastRowNum: %s", firstRowNum, lastRowNum);
            for (int i = firstRowNum; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                List<String> list = new ArrayList<>();
                short firstCellNum = row.getFirstCellNum();
                short lastCellNum = row.getLastCellNum();
                for (short j = firstCellNum; j <= lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    // 此处是把单元格都转换成String类型
                    String cellValue = formatter.formatCellValue(cell);
                    list.add(cellValue);
                }
                LOGGER.infof("RowData: %s", (list));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
