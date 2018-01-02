package main.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    static File src = new File(System.getProperty("user.dir")+ "\\src\\main\\testData\\test.xlsx");

    public static String ReadExcelByColandRow(int rowNum, int colNum) throws IOException {

        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wBook = new XSSFWorkbook(fis);
        XSSFSheet wsheet = wBook.getSheetAt(0);

        XSSFRow row = wsheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum);

        if(cell.getCellTypeEnum() == CellType.STRING){
            return cell.getStringCellValue();
        }
        else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }
        else
            return null;
    }

    public static void WriteExcelByColAndRow(int rowNum, int colNum,String value) throws IOException{

        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wBook = new XSSFWorkbook(fis);
        XSSFSheet wsheet = wBook.getSheetAt(0);
        XSSFRow row;
        try
        {	row = wsheet.getRow(rowNum);}
        catch(Exception e){
            row = wsheet.createRow(rowNum);
        }
        XSSFCell cell = row.createCell(colNum);
        cell.setCellValue(value);

        FileOutputStream outFile =new FileOutputStream(src);
        wBook.write(outFile);
        outFile.close();
    }

}
