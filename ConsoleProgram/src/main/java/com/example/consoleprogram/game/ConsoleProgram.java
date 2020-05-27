package com.example.consoleprogram.game;

import java.util.*;

public class ConsoleProgram{

    private Scanner scanner;

    public static void main(String[] args) throws Exception {
        new MyProgram().run();
    }

    public ConsoleProgram(){
        scanner = new Scanner(System.in);
    }

    public String readLine(String prompt){
        if (prompt.length() != 0) {
            System.out.println(prompt);
        }
        return scanner.nextLine();
    }

    public boolean readBoolean(String prompt){
        while(true){
            String input = readLine(prompt);

            if(input.equalsIgnoreCase("true")){
                return true;
            }

            if(input.equalsIgnoreCase("false")){
                return false;
            }
        }
    }

    public double readDouble(String prompt){
        while(true){
            String input = readLine(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ignored){}
        }
    }

    // Allow the user to get an integer.
    public int readInt(String prompt){
        while(true){
            String input = readLine(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ignored){}
        }
    }
}

