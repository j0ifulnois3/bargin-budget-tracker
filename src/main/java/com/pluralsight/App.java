package com.pluralsight;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        boolean appRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (appRunning) {
            System.out.println("Welcome to Joi's Bargain Budget Book!\n");
            System.out.println("Please select from the following menu options:\n" +
                    "To make a deposit, please enter (D)\n" +
                    "To make a payment, please enter (P)\n" +
                    "To check your account balance, please enter (B)\n"+
                    "To exit the app, please enter (X)\n");
            String userSelection = scanner.nextLine().toUpperCase();

            switch (userSelection) {
                case "D":
                    System.out.println("Please enter the following deposit information:\n");
                    break;
                case "P":
                    System.out.println("Please enter the following information to make a withdraw:\n");
                    break;
                case "B":
                    System.out.println("Your current balance and account information is:\n");
                    break;
                case "X":
                    System.out.println("Thank you for using Joi's Bargain Budget Book! Goodbye.");
                    appRunning = false;
                    break;
                default:
                    System.out.println("And I Oop, sorry I dont know what you're talking, ");
            }

        }

    }
}
