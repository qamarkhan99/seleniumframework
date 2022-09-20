package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {
    private FileInputStream fis;
    private FileOutputStream fos;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private CellStyle style;
    String path;

   public Excel(String path){
        this.path=path;
    }
    public int getRowCount(String sheetName) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
       int rowCount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowCount;
    }

    public int getCellCount(String sheetName,int rowNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fis.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        DataFormatter dataFormatter = new DataFormatter();
        String data;
        try{
            data = dataFormatter.formatCellValue(cell);
        }
        catch (Exception e){
            data="";
        }
        workbook.close();
        fis.close();
        return data;
    }

    /*public void setCellData(String sheetName,int rowNum,int colNum,String dara) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(dara);

        fos = new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();

    }
    */

    public void fillGreenColor(String sheetName,int rowNum,int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();

    }
    public void fillRedColor(String sheetName,int rowNum,int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }

}
