package SRMSMiniProject1.StuedentManagement;

import java.util.Scanner;

// To create the interface for Stduent Management System, 
// we can define an interface called StudentManagement. 
// This interface will declare the methods that need to be implemented by any class that wants to manage students. 
// Here's an example of how the StudentManagement interface can be defined:

public interface StudentManagement {

    void addStudentDetails(Scanner scanner);

    void viewStudentDetails(Scanner scanner);

    void updateStudentDetails();

    // void deleteStudentDetails();

}
