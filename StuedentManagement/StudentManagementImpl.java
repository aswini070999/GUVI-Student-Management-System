package SRMSMiniProject1.StuedentManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementImpl implements StudentManagement {

    private ArrayList<Student> students;
    private FileHandler fileHandler;

    public StudentManagementImpl() {
        fileHandler = new FileHandler();
        students = fileHandler.loadStudents();
    }

    @Override
    public void addStudent(Scanner scanner) {

        int id = getNextStudentId();

        System.out.println("Student ID: " + id);

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Age: ");
        int age;

        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid age.");
            return;
        }

        if (age < 18) {
            System.out.println("Student age must be 18 or older.");
            return;
        }

        System.out.print("Enter Student Batch: ");
        String batch = scanner.nextLine();

        System.out.print("Enter Class Timing: ");
        String timing = scanner.nextLine();

        Student student = new Student(
                id, name, age, batch, timing);

        students.add(student);

        if (fileHandler.saveStudents(students)) {
            System.out.println("Student added successfully!");
        } else {
            students.remove(student);
            System.out.println("Unable to save student details.");
        }
    }

    @Override
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\nStudent List:");

        for (Student student : students) {
            displayStudent(student);
        }
    }

    @Override
    public void updateStudent(Scanner scanner) {

        System.out.print("Enter Student ID to update: ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Student ID.");
            return;
        }

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        String oldName = student.getStudentName();
        int oldAge = student.getStudentAge();
        String oldBatch = student.getStudentBatch();
        String oldTiming = student.getStudentClassTiming();

        System.out.print("Enter new Name: ");
        student.setStudentName(scanner.nextLine());

        System.out.print("Enter new Age: ");

        int age;

        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid age.");
            student.setStudentName(oldName);
            return;
        }

        if (age < 18) {
            System.out.println("Student age must be 18 or older.");
            student.setStudentName(oldName);
            return;
        }

        student.setStudentAge(age);

        System.out.print("Enter new Batch: ");
        student.setStudentBatch(scanner.nextLine());

        System.out.print("Enter new Class Timing: ");
        student.setStudentClassTiming(scanner.nextLine());

        if (fileHandler.saveStudents(students)) {
            System.out.println("Student updated successfully!");
        } else {
            student.setStudentName(oldName);
            student.setStudentAge(oldAge);
            student.setStudentBatch(oldBatch);
            student.setStudentClassTiming(oldTiming);

            System.out.println("Unable to save changes.");
        }
    }

    @Override
    public void deleteStudent(Scanner scanner) {

        System.out.print("Enter Student ID to delete: ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Student ID.");
            return;
        }

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);

        if (fileHandler.saveStudents(students)) {
            System.out.println("Student deleted successfully!");
        } else {
            students.add(student);
            System.out.println("Unable to save changes.");
        }
    }

    @Override
    public void searchStudent(Scanner scanner) {

        System.out.print("Enter Student ID to search: ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Student ID.");
            return;
        }

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            displayStudent(student);
        }
    }

    private int getNextStudentId() {

        int maxId = 0;

        for (Student student : students) {
            if (student.getStudentId() > maxId) {
                maxId = student.getStudentId();
            }
        }

        return maxId + 1;
    }

    private Student findStudent(int id) {

        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }

        return null;
    }

    private void displayStudent(Student student) {

        System.out.println("--------------------------");
        System.out.println("Student ID   : " + student.getStudentId());
        System.out.println("Student Name : " + student.getStudentName());
        System.out.println("Student Age  : " + student.getStudentAge());
        System.out.println("Student Batch: " + student.getStudentBatch());
        System.out.println("Class Timing : " + student.getStudentClassTiming());
    }
}