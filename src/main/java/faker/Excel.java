package faker;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Excel {


    public static String generateCEP() throws IOException {
        File file = new File("C:\\Users\\anicolle\\eclipse\\RestAssured\\src\\main\\java\\faker\\postcode_ranges.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        String randomCEP = null;
        List<String> columnNames = new ArrayList<>();
        for (Iterator<Cell> iterator = row.cellIterator(); iterator.hasNext(); ) {
            Cell cell = iterator.next();
            String value = cell.getStringCellValue();
            columnNames.add(value);
        }
        if (columnNames.contains("postcode_r")) {
            Iterator<Row> iterator = sheet.iterator();
            if (iterator.hasNext()) {
                iterator.next();
            }
            List<String> validCepsList = new ArrayList<>();
            while (iterator.hasNext()) {
                row = iterator.next();
                Cell code = row.getCell(2);
                if (code.toString().contains("end")) {
                    break;
                }
                validCepsList.add(code.toString());
            }
            Random random = new Random();
            randomCEP =  validCepsList.get(random.nextInt(validCepsList.size()));
        }
        return randomCEP;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(generateCEP());
    }
}
