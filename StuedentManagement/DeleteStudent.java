package SRMSMiniProject1.StuedentManagement;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;

public class DeleteStudent {

    public void deleteStudentDetails(Scanner scanner) {

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

                ViewStudent viewStudent = new ViewStudent();
                viewStudent.displayStudentList(workbook);

            // Ask Student ID
            System.out.println("\nStudent List is displayed above.");
            System.out.println("Please select the Student ID you want to delete.");
            System.out.print("Please enter Student ID: ");

            String input = scanner.nextLine();

            int studentId;

            try {
                studentId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Student ID.");
                workbook.close();
                return;
            }

            // Search Student ID
            Row deleteRow = null;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null)
                    continue;

                Cell idCell = row.getCell(0);

                if (idCell == null)
                    continue;

                int existingId =
                        (int) idCell.getNumericCellValue();

                if (existingId == studentId) {
                    deleteRow = row;
                    break;
                }
            }

            // Student not found
            if (deleteRow == null) {

                System.out.println("Student ID not found.");

                workbook.close();
                return;
            }

            // Show student before delete
            System.out.println("\nStudent Found");
            System.out.println("---------------------------");

            System.out.println("Student ID  : " +
                    (int) deleteRow.getCell(0).getNumericCellValue());

            System.out.println("Student Name: " +
                    deleteRow.getCell(1).getStringCellValue());

            System.out.println("Student Age : " +
                    (int) deleteRow.getCell(2).getNumericCellValue());

            System.out.println("Student Batch Name: " +
                    deleteRow.getCell(3).getStringCellValue());

            System.out.println("Class Timing: " +
                    deleteRow.getCell(4).getStringCellValue());

            System.out.println("---------------------------");

            // Confirm delete
            System.out.print("Are you sure you want to delete? (yes/no): ");

            String answer = scanner.nextLine();

            if (!answer.equalsIgnoreCase("yes")) {

                System.out.println("Delete cancelled.");

                workbook.close();
                return;
            }

            // Delete row
            int rowIndex = deleteRow.getRowNum();

            sheet.removeRow(deleteRow);

            // Shift rows upward
            if (rowIndex < sheet.getLastRowNum()) {
                sheet.shiftRows(
                        rowIndex + 1,
                        sheet.getLastRowNum(),
                        -1
                );
            }

            // Save into temporary Excel file
            output = new FileOutputStream(tempFile);

            workbook.write(output);

            output.close();
            output = null;

            workbook.close();
            workbook = null;

            // Replace original file
            if (file.delete()) {

                if (tempFile.renameTo(file)) {

                    System.out.println(
                            "\nStudent deleted successfully!"
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
                    "Error while deleting student: " + e
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