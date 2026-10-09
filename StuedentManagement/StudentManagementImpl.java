
package SRMSMiniProject1.StuedentManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementImpl implements StudentManagement {

    private ArrayList<Student> students;
    private FileHandler fileHandler;

    public StudentManagementImpl() {
        fileHandler = new FileHandler();
        students = fileHandler.loadStudents();

        if (students == null) {
            students = new ArrayList<>();
        }
    }

    // Add Student
    @Override
    public void addStudent(Scanner scanner) {

        int id = getNextStudentId();

        System.out.println("Student ID: " + id);

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        System.out.print("Enter Student Age: ");
        int age;

        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid age.");
            return;
        }

        if (age < 18) {
            System.out.println("Student age must be 18 or older.");
            return;
        }

        System.out.print("Enter Student Batch: ");
        String batch = scanner.nextLine().trim();

        if (batch.isEmpty()) {
            System.out.println("Student batch cannot be empty.");
            return;
        }

        System.out.print("Enter Class Timing: ");
        String timing = scanner.nextLine().trim();

        if (timing.isEmpty()) {
            System.out.println("Class timing cannot be empty.");
            return;
        }

        Student student = new Student(id, name, age, batch, timing);

        students.add(student);

        if (fileHandler.saveStudents(students)) {
            System.out.println("Student added successfully!");
        } else {
            students.remove(student);
            System.out.println("Unable to save student details.");
        }
    }

    // View Students
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

    // Update Student
    @Override
    public void updateStudent(Scanner scanner) {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        viewStudents();

        System.out.print("Enter Student ID to update: ");
        int id;

        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Student ID.");
            return;
        }

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        // Collect and validate new details first
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        System.out.print("Enter new Age: ");
        int age;

        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid age.");
            return;
        }

        if (age < 18) {
            System.out.println("Student age must be 18 or older.");
            return;
        }

        System.out.print("Enter new Batch: ");
        String batch = scanner.nextLine().trim();

        if (batch.isEmpty()) {
            System.out.println("Student batch cannot be empty.");
            return;
        }

        System.out.print("Enter new Class Timing: ");
        String timing = scanner.nextLine().trim();

        if (timing.isEmpty()) {
            System.out.println("Class timing cannot be empty.");
            return;
        }

        // Keep old details for rollback
        String oldName = student.getStudentName();
        int oldAge = student.getStudentAge();
        String oldBatch = student.getStudentBatch();
        String oldTiming = student.getStudentClassTiming();

        // Apply updates only after validation
        student.setStudentName(name);
        student.setStudentAge(age);
        student.setStudentBatch(batch);
        student.setStudentClassTiming(timing);

        if (fileHandler.saveStudents(students)) {
            System.out.println("Student updated successfully!");
        } else {
            student.setStudentName(oldName);
            student.setStudentAge(oldAge);
            student.setStudentBatch(oldBatch);
            student.setStudentClassTiming(oldTiming);

            System.out.println(
                    "Unable to save changes. Original details restored.");
        }
    }

    // Delete Student
    @Override
    public void deleteStudent(Scanner scanner) {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        // Display list before asking for ID
        viewStudents();

        System.out.print("Enter Student ID to delete: ");
        int id;

        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Student ID.");
            return;
        }

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        // Preserve the original position
        int index = students.indexOf(student);
        students.remove(index);

        if (fileHandler.saveStudents(students)) {
            System.out.println("Student deleted successfully!");
        } else {
            students.add(index, student);

            System.out.println(
                    "Unable to save changes. Student details restored.");
        }
    }

    // Search Student
    @Override
    public void searchStudent(Scanner scanner) {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.print("Enter Student ID to search: ");
        int id;

        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Student ID.");
            return;
        }

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("\nStudent Details:");
            displayStudent(student);
        }
    }

    // Generate the next Student ID
    private int getNextStudentId() {

        int maxId = 0;

        for (Student student : students) {
            if (student.getStudentId() > maxId) {
                maxId = student.getStudentId();
            }
        }

        return maxId + 1;
    }

    // Find Student by ID
    private Student findStudent(int id) {

        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }

        return null;
    }

    // Display Student Details
    private void displayStudent(Student student) {

        System.out.println("--------------------------");
        System.out.println("Student ID   : " + student.getStudentId());
        System.out.println("Student Name : " + student.getStudentName());
        System.out.println("Student Age  : " + student.getStudentAge());
        System.out.println("Student Batch: " + student.getStudentBatch());
        System.out.println("Class Timing : " + student.getStudentClassTiming());
        System.out.println("--------------------------");
    }
}