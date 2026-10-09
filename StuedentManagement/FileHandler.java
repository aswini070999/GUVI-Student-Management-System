package SRMSMiniProject1.StuedentManagement;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String FILE_NAME = "students.txt";

    // Load student details from text file
    public ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            // Skip the column headings
            String line = reader.readLine();

            // Skip the separator line
            line = reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length != 5) {
                    System.out.println("Skipping invalid record.");
                    continue;
                }

                try {
                    Student student = new Student(
                        Integer.parseInt(data[0].trim()),
                        data[1].trim(),
                        Integer.parseInt(data[2].trim()),
                        data[3].trim(),
                        data[4].trim()
                    );

                    students.add(student);

                } catch (NumberFormatException e) {
                    System.out.println(
                        "Invalid student ID or age."
                    );
                }
            }

        } catch (IOException e) {
            System.out.println(
                "Error reading student file: " + e.getMessage()
            );
        }

        return students;
    }

    // Save student details with aligned columns
    public boolean saveStudents(ArrayList<Student> students) {

        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(FILE_NAME))) {

            // Column headings
            writer.write(String.format(
                "%-12s | %-20s | %-12s | %-16s | %-15s",
                "Student ID",
                "Student Name",
                "Student Age",
                "Student Batch",
                "Class Timing"
            ));

            writer.newLine();

            // Separator line
            writer.write(
                "-----------------------------------------------------------------------------------------"
            );

            writer.newLine();

            // Student records
            for (Student student : students) {

                writer.write(String.format(
                    "%-12d | %-20s | %-12d | %-16s | %-15s",
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getStudentAge(),
                    student.getStudentBatch(),
                    student.getStudentClassTiming()
                ));

                writer.newLine();
            }

            return true;

        } catch (IOException e) {
            System.out.println(
                "Error saving student file: " + e.getMessage()
            );

            return false;
        }
    }

    // Generate the next Student ID
    public int getStudentId() {

        ArrayList<Student> students = loadStudents();

        int maxId = 0;

        for (Student student : students) {

            if (student.getStudentId() > maxId) {
                maxId = student.getStudentId();
            }
        }

        return maxId + 1;
    }
}