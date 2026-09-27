package SRMSMiniProject1.StuedentManagement;

import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;

public class SearchStudent {

    public Row searchStudentDetailsForView(Scanner scanner, Workbook workbook) 
    {

        Sheet sheet = workbook.getSheet("Students");

        System.out.println("\nPlease enter the Student ID to search.");
        System.out.println("If you don't want to search, please enter 'exit'.");
        System.out.print("Enter Student ID: ");

        String input = scanner.nextLine();


        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting search...");
            return null;
        }

        int getStudentId = Integer.parseInt(input);

        boolean found = false;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null)
                continue;

            Cell idCell = row.getCell(0);

            if (idCell == null)
                continue;

            int existingId =
                    (int) idCell.getNumericCellValue();

            if (existingId == getStudentId) {

                System.out.println("\nStudent Details");
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

                found = true;

                return row;
            }
        }

        if (!found) {
            System.out.println("Student ID not found!");
        }

        return null;
    }
 public Row searchStudentForUpdate(Scanner scanner, Workbook workbook) {

        Sheet sheet = workbook.getSheet("Students");
        
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

            return null;
        }

        // Search student
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

                return row;
            }
        }

        System.out.println("Student ID not found!");

        return null;
    }
}
