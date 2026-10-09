
import java.util.Scanner;

import SRMSMiniProject1.StuedentManagement.StudentManagement;
import SRMSMiniProject1.StuedentManagement.StudentManagementImpl;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentManagement studentManagement = new StudentManagementImpl();

        System.out.print("Are you a GUVI student? (yes/no): ");
        String continueInput = scanner.nextLine();

        if (!continueInput.equalsIgnoreCase("yes")) {
            System.out.println(
                    "Thank you for visiting the GUVI Student Management System!");
            scanner.close();
            return;
        }

        System.out.println(
                "Hi! Welcome to the GUVI Student Management System!");

        System.out.print(
                "Do you want to continue with the Student Management System? (yes/no): ");

        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println(
                    "Thank you! Please visit us again.");
            scanner.close();
            return;
        }

        while (true) {

            System.out.println("\n===== GUVI STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("Add Student    : add");
            System.out.println("View Students  : view");
            System.out.println("Update Student : update");
            System.out.println("Delete Student : delete");
            System.out.println("Search Student : search");
            System.out.println("Exit           : exit");

            System.out.print("Please select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice.toLowerCase()) {

                case "add":
                    studentManagement.addStudent(scanner);
                    break;

                case "view":
                    studentManagement.viewStudents();
                    break;

                case "update":
                    studentManagement.updateStudent(scanner);
                    break;

                case "delete":
                    studentManagement.deleteStudent(scanner);
                    break;

                case "search":
                    studentManagement.searchStudent(scanner);
                    break;

                case "exit":
                    System.out.println(
                            "Thank you for using the GUVI Student Management System!");
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid option. Please enter add, view, update, delete, search, or exit.");
            }
        }
    }
}