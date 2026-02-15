package Action;

import model.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateData {

    public static List<Student> readSheet(String fileName) {

        List<Student> students = new ArrayList<>();

        try (InputStream is = CreateData.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            // if (is == null) {
            //     throw new RuntimeException("Không tìm thấy file: " + fileName);
            // }

            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                int year = (int) row.getCell(2).getNumericCellValue();
                String address = row.getCell(3).getStringCellValue();
                String phone = row.getCell(4).getStringCellValue();
                String email = row.getCell(5).getStringCellValue();
                String className = row.getCell(6).getStringCellValue();

                students.add(new Student(
                        id, name, year,
                        address, phone, email, className));
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}
