import java.util.Scanner;

import SRMSMiniProject1.StuedentManagement.AddStudent;
import SRMSMiniProject1.StuedentManagement.ViewStudent;
import SRMSMiniProject1.StuedentManagement.UpdateStudent;
import SRMSMiniProject1.StuedentManagement.DeleteStudent;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AddStudent addStudent = new AddStudent(0,null, 0, null, null);
        ViewStudent viewStudent = new ViewStudent();
        UpdateStudent updateStudentDetails = new UpdateStudent();
        DeleteStudent deleteStudentDetails = new DeleteStudent();


        System.out.print("Are You GUVI Student? (yes/no): ");

        String Continue = scanner.nextLine();

        if (Continue.equalsIgnoreCase("yes")) 
        {
            System.out.println("Hi! Welcome to the GUVI Student Management System!");
        }else {
            System.out.print("Thank you for visiting the GUVI Student Management System!");
            return;
        }

        System.out.print("Do you want to continue with Student Management System? (yes/no): ");

        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) 
        {
            System.out.println("Please select an option from the menu below:");

            System.out.println("Add Student: Just Give add");
            System.out.println("View Students: just Give view");
            System.out.println("Update Student: Just Give update");
            System.out.println("Delete Student: Just Give delete");
            System.out.println("Exit: Just Give exit");
            
        }else if(answer.equalsIgnoreCase("No")){
            System.out.println("Thank You....! and Pls Visit our GUVI Student Management System!!");
            return;
        }
         
        String choice = scanner.nextLine();


            switch (choice) {
                case "add":
                    addStudent.addStudentDetails(scanner);
                    break;
                    
                case "view":
                    viewStudent.viewStudentDetails(scanner);
                    break;

                case "update":
                    updateStudentDetails.updateStudentDetails(scanner);
                    break;

                case "delete":
                    deleteStudentDetails.deleteStudentDetails(scanner);
                    break;

                case "exit":
                    System.out.println("Exiting...!");
                    System.out.println("Thank you for using the GUVI Student Management System.");

                    scanner.close();
                    return;

                default:
                    System.out.println("Please enter a valid option and join our GUVI Student Management System!");                    
                    break;

            }
    
    }
}
