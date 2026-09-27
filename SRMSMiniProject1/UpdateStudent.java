package SRMSMiniProject1.SRMSMiniProject1;

import java.io.util.scanner;

import org.apache.poi.ss.usermodel.*;

public class UpdateStudent {
    
    public void updateStudentDetails(){

        String fileName = "Guvi students.xlsx";
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) 
        {
            System.out.println("No student records found.");
            return;
        }

        

    }
}
