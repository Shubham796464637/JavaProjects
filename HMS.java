package HosptalManagementSystem;

import javax.print.Doc;
import java.sql.*;
import java.util.Scanner;

public class HMS {
    private static final String url="jdbc:mysql://localhost:3306/hospital";
    private static final String usename="root";
    private static final String password="shubh@222101@";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        Scanner sc=new Scanner(System.in);
        try
        {
            Connection conn= DriverManager.getConnection(url,usename,password);
            Patient p1=new Patient(conn,sc);
            Doctor d1=new Doctor(conn);

            while(true) {
                System.out.println("*-----HOSPITAL MANAGEMENT SYSTEM-----*");
                System.out.println("1:   Add Patient   :-");
                System.out.println("2:   View Patient  :-");
                System.out.println("3:   View Doctor   :-");
                System.out.println("4:  Book Appointment  :-");
                System.out.println("5:      Exit  :-");

                System.out.println("Enter your choice:");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        // Add Patient
                        p1.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        //
                        p1.viewPatient();
                        System.out.println();
                        break;
                    case 3:
                        //
                        d1.viewDoctor();
                        System.out.println();
                        break;
                    case 4:
                        //
                        bookAppointment(p1,d1,conn,sc);
                        System.out.println();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice....");
                }
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void bookAppointment(Patient patient,Doctor doctor,Connection connection,Scanner scanner) {
        System.out.println("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.println("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.println("Enter appointment date (YYYY-MM-DD):   ");
        String appointmentDate = scanner.next();

        if (patient.getPatientId(patientId) && doctor.getDoctorId(doctorId)) {

            if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {
                String q = "insert into appointment(patient_id,doctor_id,app_date) values(?,?,?)";
                try {
                    PreparedStatement pre = connection.prepareStatement(q);
                    pre.setInt(1, patientId);
                    pre.setInt(2, doctorId);
                    pre.setString(3, appointmentDate);

                    int rowAffected = pre.executeUpdate();
                    if (rowAffected > 0) {
                        System.out.println("Appointment Book !!!!!");
                    } else {
                        System.out.println("Failed to book appointments!!!!!");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Doctor not available on this date!!!!!");
            }
        } else
        {

            System.out.println("Either Patient or doctor doesn't exits !!!!!!!!!");
        }
    }
    public static boolean checkDoctorAvailability(int doctorID,String appointmentDate,Connection connection)
    {
        String q="Select count(*) from appointment where doctor_id= ? and app_date=?";
        try {
            PreparedStatement pre=connection.prepareStatement(q);
            pre.setInt(1,doctorID);
            pre.setString(2,appointmentDate);
            ResultSet resultSet=pre.executeQuery();
            if(resultSet.next())
            {
                int count=resultSet.getInt(1);
                if(count==0)
                {
                    return true;
                }
                else {
                    return false;
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
