package com.project;

import java.util.Scanner;

class ATM{

    Scanner st=new Scanner(System.in);

    float balance;
    int pin=7283;

        public void checkPin ()
        {
            System.out.println("Enter your Pin:");
            int p = st.nextInt();

            if (pin == p) {
                menu();
            }
            else {
                System.out.println("you entered wrong PIN !!! Pls Try again:--");
               checkPin();
            }
        }
        public void menu ()
        {
            System.out.println(":---MENU----:");
            System.out.println("1: Display the Balance of Account :-");
            System.out.println("2: Deposit the Balance of Account :-");
            System.out.println("3: Withdraw the Balance of Account :-");
            System.out.println("4: Exit :-");

            System.out.println("Enter your choice:-");
            int ch = st.nextInt();

            switch (ch) {
                case 1: {
                   displayBalance();
                    break;
                }
                case 2: {
                    depositBalance();
                    break;
                }
                case 3: {
                    withdrawBalance();
                    break;
                }
                case 4: {
                    break;
                }
                default:
                    System.out.println("Invalid choice you entered wrong Input:-");
            }
        }
        public void displayBalance ()
        {
            System.out.println("Your Total Balance=" + balance);
            menu();
        }
        public void withdrawBalance ()
        {

            System.out.println("Enter your amount.! you want to withdraw :-");
            float amount = st.nextFloat();

            if (amount > balance) {
                System.out.println("Balance is not a Sufficient!!!!!!");
            }
            else {
                balance-=amount;
            }
            System.out.println("your balance after withdraw :-"+balance);
            menu();
        }
        public void depositBalance ()
        {
            System.out.println("Enter your amount.! you want to deposit :-");
            float amount = st.nextFloat();

            balance+=amount;
            System.out.println("Your balance after Deposit :-" + balance);

            menu();
        }
    }
public class BasicATMSystem {
    public static void main(String[] args) {

        ATM a1=new ATM();
        a1.checkPin();
    }
}
