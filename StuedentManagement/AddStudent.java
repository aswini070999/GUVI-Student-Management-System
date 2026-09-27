package SRMSMiniProject1.StuedentManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class AddStudent {

    private int studentId;
    private String studentName;
    private int studentAge;
    private String studentBatchName;
    private String studentClassTiming;

    private ArrayList<AddStudent> students = new ArrayList<>();
    FileHandler fileHandle = new FileHandler();

    // Here useing using construtor:
public AddStudent(
        int studentId,
        String studentName,
        int studentAge,
        String studentBatchName,
        String studentClassTiming) 
        {
       
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentBatchName = studentBatchName;
        this.studentClassTiming = studentClassTiming;
        }

    public void addStudentDetails(Scanner scanner) 
    {
    
    while (true) {

    System.out.println("Sure! Please enter the student details below.");                

    System.out.print("Enter Student Name: ");
    studentName = scanner.nextLine();

    while (true) {

        try {
            System.out.print("Enter Student Age: ");
            studentAge = Integer.parseInt(scanner.nextLine());

            if (studentAge < 18)
            {
                throw new IllegalArgumentException( "Student age must be 18 or older.");
            }
                break;  // correct age → exit age loop

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Age: " + e.getMessage());
            continue;
        }
    }
        System.out.print("Enter Student Batch Name: ");
        studentBatchName = scanner.nextLine();

        System.out.print("Enter Student class Timings: ");
        studentClassTiming = scanner.nextLine();

        int nextId = FileHandler.getStudentId();


        // Create student object
        AddStudent student = new AddStudent(
            nextId,
            studentName,
            studentAge,
            studentBatchName,
            studentClassTiming
        );

        // Send student details to FileHandler
       boolean saved = FileHandler.saveStudent(student);

    if (saved) {

        // Add student object to ArrayList
        students.add(student);
            
        System.out.println("Student ID           : " + student.getStudentId());
        System.out.println("Student Name         : " + student.getStudentName());
        System.out.println("Student Age          : " + student.getStudentAge());
        System.out.println("Student Batch Name   : " + student.getStudentBatchName());
        System.out.println("Student Class Timing : " + student.getStudentClassTiming());
    } else {
        System.out.println("\nStudent was not saved.");
    }
        System.out.print("\nDo you want to add another student? (yes/no): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("no")) 
        {
         System.out.print("Exiting.....!");
            break;
        }
    }
}

        public int getStudentId() {
            return studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public int getStudentAge() {
            return studentAge;
        }

        public String getStudentBatchName() {
            return studentBatchName;
        }

        public String getStudentClassTiming() {
            return studentClassTiming;
        }
          
}