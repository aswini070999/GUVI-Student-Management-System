package SRMSMiniProject1.StuedentManagement;

import java.util.Scanner;

public interface StudentManagement {

    void addStudent(Scanner scanner);

    void viewStudents();

    void updateStudent(Scanner scanner);

    void deleteStudent(Scanner scanner);

    void searchStudent(Scanner scanner);
}