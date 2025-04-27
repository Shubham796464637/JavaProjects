package com.project;

import java.util.ArrayList;

abstract class Employee{

    private int id;
    private String name;

    public Employee(int id,String name)
    {
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;

    }
    @Override
    public String toString()
    {
        return " | Employee ID :- " + id + " | Employee Name :-" + name  + " | Employee Salary :- " + calculateSalary() ;
    }

    abstract public double calculateSalary();
}

class FullTimeEmployee extends Employee{

     private double monthSalary;

    public FullTimeEmployee(int id ,String name,double monthSalary)
    {
        super(id,name);
        this.monthSalary=monthSalary;
    }

    @Override
    public double calculateSalary() {
        return monthSalary;
    }
}

class PartTimeEmployee extends Employee{

     private int hourWork;
     private double hourRate;

     public PartTimeEmployee(int id,String name,int hourWork,double hourRate)
     {
         super(id,name);
         this.hourWork=hourWork;
         this.hourRate=hourRate;
     }

    @Override
    public double calculateSalary() {
        return hourWork*hourRate;
    }
}
class PayrollSystem{

     private ArrayList<Employee> list1;

     public PayrollSystem() {

         list1 = new ArrayList<>();
     }

     public void addEmployee(Employee employee)
     {
         list1.add(employee);
     }
     public void removeEmployee(int id){

         Employee removeEmployee=null;

         for(Employee employee: list1){
             if(employee.getId()==id)
             {
                 removeEmployee=employee;
                 break;
             }
         }
         if(removeEmployee!=null)
         {
             list1.remove(removeEmployee);

             System.out.println(removeEmployee);
         }

     }
     public void displayEmployee(){

         for(Employee employee: list1)
         {
             System.out.println(employee);
         }
     }
}

public class EmployeePayrollSystem {

    public static void main(String[] args) {

        PayrollSystem p1=new PayrollSystem();

        FullTimeEmployee emp1=new FullTimeEmployee(101,"shubham",70000.00);

        PartTimeEmployee emp2=new PartTimeEmployee(102,"Neel",30,120.00);

        p1.addEmployee(emp1);
        p1.addEmployee(emp2);

        System.out.println("Initial Employee Details :-");
        p1.displayEmployee();

        System.out.println("Removing Employee :-");
        p1.removeEmployee(101);

        System.out.println("Remaining Employee :-");
        p1.displayEmployee();

    }
}
