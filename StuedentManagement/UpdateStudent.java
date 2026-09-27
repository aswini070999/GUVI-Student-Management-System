package SRMSMiniProject1.StuedentManagement;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;

public class UpdateStudent {

    public void updateStudentDetails(Scanner scanner) {

        String fileName = "Guvi students.xlsx";
        String tempFileName = "Guvi students_temp.xlsx";

        File file = new File(fileName);
        File tempFile = new File(tempFileName);

        if (!file.exists() || file.length() == 0) {

            System.out.println("No student records found.");
            return;
        }

        Workbook workbook = null;
        FileOutputStream output = null;

        try {

            // Open Excel
            workbook = WorkbookFactory.create(file);

            Sheet sheet = workbook.getSheet("Students");

            if (sheet == null) {

                System.out.println("No student records found.");
                workbook.close();
                return;
            }
            

            // Display student list before update
            ViewStudent viewStudent = new ViewStudent();
            viewStudent.displayStudentList(workbook);

            // Search student for UPDATE
            SearchStudent searchStudent = new SearchStudent();

            Row row = searchStudent.searchStudentForUpdate(
                    scanner,
                    workbook
            );

            if (row == null) {

                workbook.close();
                return;
            }

            // Show existing student
            System.out.println("\nStudent Found");
            System.out.println("---------------------------");

            System.out.println("Student ID  : " +
                    (int) row.getCell(0).getNumericCellValue());

            System.out.println("Student Name: " +
                    row.getCell(1).getStringCellValue());

            System.out.println("Student Age : " +
                    (int) row.getCell(2).getNumericCellValue());

            System.out.println("Student Batch Name: " +
                    row.getCell(3).getStringCellValue());

            System.out.println("Class Timing: " +
                    row.getCell(4).getStringCellValue());

            System.out.println("---------------------------");


            // =========================
            // UPDATE DETAILS
            // =========================

            System.out.println("\nUpdate Student Details");
            System.out.println("---------------------------");

            System.out.print("Enter new Student Name: ");
            String studentName = scanner.nextLine();

            System.out.print("Enter new Student Age: ");
            int studentAge = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new Student Batch Name: ");
            String studentBatchName = scanner.nextLine();

            System.out.print("Enter new Class Timing: ");
            String classTiming = scanner.nextLine();


            // Update cells
            row.getCell(1).setCellValue(studentName);
            row.getCell(2).setCellValue(studentAge);
            row.getCell(3).setCellValue(studentBatchName);
            row.getCell(4).setCellValue(classTiming);


            // =========================
            // SAVE TO TEMP FILE
            // =========================

            output = new FileOutputStream(tempFile);

            workbook.write(output);

            output.close();
            output = null;

            workbook.close();
            workbook = null;


            // =========================
            // REPLACE ORIGINAL FILE
            // =========================

            if (file.delete()) {

                if (tempFile.renameTo(file)) {

                    System.out.println(
                            "\nStudent updated successfully!"
                    );

                } else {

                    System.out.println(
                            "Could not rename temporary file."
                    );
                }

            } else {

                System.out.println(
                        "Could not replace original Excel file."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while updating student: "
                    + e
            );

        } finally {

            try {

                if (output != null) {
                    output.close();
                }

                if (workbook != null) {
                    workbook.close();
                }

            } catch (Exception e) {
                // Ignore closing error
            }
        }
    }
}