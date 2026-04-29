package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

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
                case "D": {
                    String timestamp = getCurrentTimestamp();
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

                    System.out.println("Deposit Completed on " + date + " at " + time + "\n");
                }

                break;
                case "P": {
                    String timestamp = getCurrentTimestamp();
                    String[] parts = timestamp.split("\\|");
                    String date = parts[0];
                    String time = parts[1];

                    System.out.println("Apply Payment Description:");
                    String description = scanner.nextLine();

                    System.out.println("Enter Payment Recipient:");
                    String vendor = scanner.nextLine();

                    System.out.println("Enter Payment Amount:");
                    double amt = scanner.nextDouble();
                    amt = amt * -1;
                    scanner.nextLine();

                    Transaction t = new Transaction(date, time, description, vendor, amt);
                    transactions.add(t);

                    System.out.println("Payment Completed and Logged on " + date + " at " + time + "\n");
                }

                break;
                case "B":
                    double total = 0;
                    for (Transaction t : transactions) {
                        total += t.getAmount();
                    }
                    System.out.printf("Current Account Balance: $%.2f\n", total);
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

    public static void loadTransactions(ArrayList<Transaction> transactions) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                // Map the parts back to the constructor: date, time, desc, vendor, amount
                double amount = Double.parseDouble(parts[4]);
                Transaction t = new Transaction(parts[0], parts[1], parts[2], parts[3], amount);
                transactions.add(t);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No previous history found. Starting a fresh book!");
        }
    }

    public static void saveTransaction(Transaction t) {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));

            String line = String.format("%s|%s|%s|%s|%.2f",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("And I Oop! I couldn't save that record, girl.");
        }
    }


}




