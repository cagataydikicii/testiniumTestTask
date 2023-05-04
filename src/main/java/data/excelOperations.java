package data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;
import utiliy.log;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class excelOperations {
    log log = new log();
    static String fileName;
    static FileInputStream file;
    public Boolean controlFile (String filePath, String fileName){
        boolean file = false;
        if(!isFileExist(filePath,fileName)){
            log.error(filePath+fileName+" isimli dosya bulunmamaktadır");
            file = true;
        }
        return  file;
    }

    public boolean isFileExist(String filePath, String fileName){
        boolean flag = false;
        File dir = new File(filePath);
        for(int count=2; !flag&&count>0;count--){
            log.info(fileName+" isimli dosya kontrol ediliyor. Kontrol : "+count);
            File[] dir_contents = dir.listFiles();
            for (int i =0; i<dir_contents.length;i++){
                if(dir_contents[i].getName().equals(fileName)){
                    log.info(fileName+" isimli dosya var.");
                    return flag = true;
                }
            }

        }

        return flag;
    }

    public List<String[]> excelReadtoList(String filePath, String fileName){
        List<String[]> tempList = new ArrayList<String[]>();
        try {
            file = new FileInputStream(new File(filePath+fileName));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            DataFormatter df = new DataFormatter();
            Sheet sheet = workbook.getSheet("sheet");
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()){
                String row[] = new String[10];
                Row rows = rowIterator.next();
                Iterator<Cell> cellIterator = rows.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    row[cell.getColumnIndex()]=df.formatCellValue(cell);
                }
                tempList.add(row);
            }


        }catch (Exception e){
            log.error("Hata tempList doldurulurken hata alindi"+ e.getMessage());
            throw new SkipException("Hata excelRead fonksiyonunda hata alindi");
        }

        return tempList;
    }


}
