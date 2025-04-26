package com.project;

import java.util.Scanner;

public class Games {
    public static void main(String[] args) {

        char[][] b=new char[3][3];
        for(int i=0;i<b.length;i++)
        {
            for(int j=0; j<b[i].length;j++)
            {
                b[i][j]=' ';
            }
        }

        char player='X';
        boolean gameOver=false;

        Scanner st=new Scanner(System.in);

        while(!gameOver){
            printboard(b);
            System.out.println("Player "+ player + " enter: ") ;
            int row=st.nextInt();
            int col=st.nextInt();

            if(b[row][col] == ' '){
                b[row][col]=player;
                gameOver=haveWon(b,player);
                if(gameOver){
                    System.out.println("Player "+ player + " has won... ") ;
                }
                else {
                    if(player=='X') {
                        player = 'O';
                    }
                    else{
                        player='X';
                    }
                }
            }
            else {
                System.out.println("invalid moves try again..");
            }
        }
        printboard(b);
    }
    public static boolean haveWon(char[][] b, char player)
    {
        for(int i=0;i<b.length;i++) {
            if (b[i][0] == player && b[i][1] == player && b[i][2] == player) {
                return true;
            }
        }

// column vice

            for(int j=0;j<b[0].length;j++)
            {
                if(b[0][j]==player && b[1][j]==player && b[2][j]==player)
                {
                    return true;
                }
            }

            //diagonal
        if(b[0][0]==player && b[1][1]==player && b[2][2]==player)
        {
            return true;
        }
        if(b[0][2]==player && b[1][1]==player && b[2][0]==player)
        {
            return true;
        }
        return false;
    }
    public static void printboard(char[][]b){
        for(int i=0;i<b.length;i++)
        {
            for(int j=0;j<b[i].length;j++)
            {
                System.out.print(b[i][j]+ " | ");
            }
            System.out.println();
        }
    }
}
