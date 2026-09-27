package SRMSMiniProject1.StuedentManagement;

import java.io.File;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;

public class ViewStudent {

    // Display list only using an existing workbook
    public void displayStudentList(Workbook workbook) {

        Sheet sheet = workbook.getSheet("Students");

        if (sheet == null || sheet.getLastRowNum() < 1) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n========== STUDENT LIST ==========");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null)
                continue;

            int studentId = (int) row.getCell(0).getNumericCellValue();
            String studentName = row.getCell(1).getStringCellValue();
            int studentAge = (int) row.getCell(2).getNumericCellValue();
            String studentBatchName = row.getCell(3).getStringCellValue();
            String classTiming = row.getCell(4).getStringCellValue();

            System.out.println("Student ID   : " + studentId);
            System.out.println("Student Name : " + studentName);
            System.out.println("Student Age  : " + studentAge);
            System.out.println("Batch Name   : " + studentBatchName);
            System.out.println("Class Timing : " + classTiming);
            System.out.println("----------------------------------");
        }
    }

    // Existing View operation: list + search
    public void viewStudentDetails(Scanner scanner) {

        String fileName = "Guvi students.xlsx";
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No student records found.");
            return;
        }

        try (Workbook workbook = WorkbookFactory.create(file)) {

            Sheet sheet = workbook.getSheet("Students");

            if (sheet == null) {
                System.out.println("No student records found.");
                return;
            }

            displayStudentList(workbook);

            // Search is only part of the normal View operation
            SearchStudent searchStudent = new SearchStudent();
            searchStudent.searchStudentDetailsForView(scanner, workbook);

        } catch (Exception e) {
            System.out.println("Error while reading Excel: " + e.getMessage());
        }
    }
}