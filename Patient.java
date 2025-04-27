package HosptalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    private Connection conn;
    private Scanner scanner;

    public Patient(Connection conn,Scanner scanner)
    {
        this.conn=conn;
        this.scanner=scanner;
    }

    public void addPatient()
    {
        System.out.print("Enter Patient ID:");
        int id=scanner.nextInt();
        System.out.print("Enter Patient Name:");
        String name=scanner.next();
        System.out.print("Enter Patient Age:");
        int age=scanner.nextInt();
        System.out.print("Enter Patient Gender:");
        String gender=scanner.next();

        try
        {
            String q="insert into patient(id,name,age,gender)values(?,?,?,?)";
            PreparedStatement pre=conn.prepareStatement(q);
            pre.setInt(1,id);
            pre.setString(2,name);
            pre.setInt(3,age);
            pre.setString(4,gender);

            int affectionRow =pre.executeUpdate();

            if(affectionRow>0)
            {
                System.out.println("Patient added Successfully!!!");
            }
            else {
                System.out.println("Failed to add Patient..");
            }
        }catch (SQLException e)
        {

        }
    }
    public void viewPatient()
    {
        String q="select * from patient";
        try
        {
            PreparedStatement pre=conn.prepareStatement(q);
            ResultSet resultSet=pre.executeQuery();
            System.out.println("Patient:");
            System.out.println("|------------|------------------|-----------|----------|");
            System.out.println("| Patient ID | Name             | Age       | Gender   |");
            System.out.println("|------------|------------------|-----------|----------|");
            while(resultSet.next())
            {
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age=resultSet.getInt("age");
                String gender=resultSet.getString("gender");

                System.out.printf("|%-12s|%-18s|%-10s |%-10s|\n",id,name,age,gender);
                System.out.println("|------------|------------------|-----------|----------|");

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean getPatientId(int id)
    {
        String q="select * from patient where id =?";

        try {

            PreparedStatement pre=conn.prepareStatement(q);
            pre.setInt(1,id);
            ResultSet resultSet=pre.executeQuery();

            if(resultSet.next())
            {
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
