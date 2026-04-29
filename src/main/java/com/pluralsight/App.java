package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App {

    public static void main(String[] args) {
        boolean appRunning = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();
        while (appRunning) {
            System.out.println("Welcome to Joi's Bargain Budget Book!\n");
            System.out.println("Please select from the following menu options:\n" +
                    "To make a deposit, please enter (D)\n" +
                    "To make a payment, please enter (P)\n" +
                    "To check your account balance, please enter (B)\n" +
                    "To exit the app, please enter (X)\n");
            String userSelection = scanner.nextLine().toUpperCase();

            switch (userSelection) {
                case "D":

                { String timestamp = getCurrentTimestamp();
                    String[] parts = timestamp.split("\\|");
                    String date = parts[0];
                    String time = parts[1];

                    System.out.println("Apply Deposit Description:");
                    String description = scanner.nextLine();

                    System.out.println("Enter Deposit Origin:");
                    String vendor = scanner.nextLine();

                    System.out.println("Enter Deposit Amount:");
                    double amt = scanner.nextDouble();
                    scanner.nextLine();

                    Transaction t = new Transaction(date, time, description, vendor, amt);
                    transactions.add(t);

                    System.out.println("Deposit Completed on " + date + " at " + time + "\n");}

                    break;
                case "P":
                { String timestamp = getCurrentTimestamp();
                    String[] parts = timestamp.split("\\|");
                    String date = parts[0];
                    String time = parts[1];

                    System.out.println("Apply Payment Description:");
                    String description = scanner.nextLine();

                    System.out.println("Enter Payment Recipient:");
                    String vendor = scanner.nextLine();

                    System.out.println("Enter Payment Amount:");
                    double amt = scanner.nextDouble();
                    amt = amt* -1;
                    scanner.nextLine();

                    Transaction t = new Transaction(date, time, description, vendor, amt);
                    transactions.add(t);

                    System.out.println("Payment Completed and Logged on " + date + " at " + time + "\n");}

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

    public static String getCurrentTimestamp() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return date.format(dateFormatter) + "|" + time.format(timeFormatter);
    }


}




