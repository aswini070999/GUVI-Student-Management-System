package SRMSMiniProject1.StuedentManagement;

public class Student {

    private int studentId;
    private String studentName;
    private int studentAge;
    private String studentBatch;
    private String studentClassTiming;

    public Student(int studentId, String studentName,
                   int studentAge, String studentBatch,
                   String studentClassTiming) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentBatch = studentBatch;
        this.studentClassTiming = studentClassTiming;
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

    public String getStudentBatch() {
        return studentBatch;
    }

    public String getStudentClassTiming() {
        return studentClassTiming;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public void setStudentBatch(String studentBatch) {
        this.studentBatch = studentBatch;
    }

    public void setStudentClassTiming(String studentClassTiming) {
        this.studentClassTiming = studentClassTiming;
    }
}