package HosptalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private Connection conn;
    private Scanner scanner;

    public Doctor(Connection conn)
    {
        this.conn=conn;

    }


    public void viewDoctor()
    {
        String q="select * from doctor";
        try
        {
            PreparedStatement pre=conn.prepareStatement(q);
            ResultSet resultSet=pre.executeQuery();
            System.out.println("Doctor :");
            System.out.println("|------------|------------------|-----------------------------|");
            System.out.println("| Doctor ID  | Name             | Specialization              |");
            System.out.println("|------------|------------------|-----------------------------|");
            while(resultSet.next())
            {
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String speci=resultSet.getString("speci");

                System.out.printf("|%-12s|%-17s |%-30s|\n",id,name,speci);
                System.out.println("|------------|------------------|-----------------------------|");


            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean getDoctorId(int id)
    {
        String q="select * from doctor where id =?";

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
