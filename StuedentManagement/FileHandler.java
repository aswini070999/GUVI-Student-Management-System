package SRMSMiniProject1.StuedentManagement;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileHandler {

    public static boolean saveStudent(AddStudent student) {

        String fileName = "Guvi students.xlsx";
        String tempFileName = "Guvi students_temp.xlsx";

        Workbook workbook = null;
        FileOutputStream output = null;

        try {

            File file = new File(fileName);
            File tempFile = new File(tempFileName);

            // Open existing workbook or create new workbook
            if (file.exists() && file.length() > 0) {

                workbook = WorkbookFactory.create(file);

            } else {

                workbook = new XSSFWorkbook();
            }

            // Get Students sheet
            Sheet sheet = workbook.getSheet("Students");

            // Create sheet if it doesn't exist
            if (sheet == null) {

                sheet = workbook.createSheet("Students");

                Row header = sheet.createRow(0);

                header.createCell(0).setCellValue("Student ID");
                header.createCell(1).setCellValue("Student Name");
                header.createCell(2).setCellValue("Age");
                header.createCell(3).setCellValue("Batch Name");
                header.createCell(4).setCellValue("Class Timing");
            }

            // Create new row
            int rowNumber = sheet.getLastRowNum() + 1;

            Row row = sheet.createRow(rowNumber);

            row.createCell(0).setCellValue(student.getStudentId());
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getStudentAge());
            row.createCell(3).setCellValue(student.getStudentBatchName());
            row.createCell(4).setCellValue(student.getStudentClassTiming());

            // Adjust column width
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            // Save to temporary file first
            output = new FileOutputStream(tempFile);

            workbook.write(output);

            output.close();
            output = null;

            workbook.close();
            workbook = null;

            // Delete old file
            if (file.exists()) {
                file.delete();
            }

            // Rename temporary file
            if (tempFile.renameTo(file)) {

                System.out.println(
                    "\nStudent details saved to Guvi students.xlsx"
                );

                return true;

            } else {

                System.out.println("Could not replace Excel file.");
                return false;
            }

        } catch (Exception e) {

            System.out.println(
                "Error while saving student: " + e
            );

            try {

                if (output != null) {
                    output.close();
                }

                if (workbook != null) {
                    workbook.close();
                }

            } catch (Exception closeError) {
                // Ignore close error
            }

            return false;
        }
    }
public static int getStudentId() {

    String fileName = "Guvi students.xlsx";

    try {

        File file = new File(fileName);

        // Excel file doesn't exist
        if (!file.exists() || file.length() == 0) {
            return 1;
        }

        Workbook workbook = WorkbookFactory.create(file);

        Sheet sheet = workbook.getSheet("Students");

        if (sheet == null) {
            workbook.close();
            return 1;
        }

        int maxId = 0;

        // Start from row 1 because row 0 is header
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            Cell cell = row.getCell(0);

            if (cell == null) {
                continue;
            }

            if (cell.getCellType() == CellType.NUMERIC) {

                int currentId = (int) cell.getNumericCellValue();

                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        }

        workbook.close();

        return maxId + 1;

    } catch (Exception e) {

        System.out.println("Error while getting Student ID: " + e);

        return 1;
    }
}
}