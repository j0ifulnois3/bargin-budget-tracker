package com.pluralsight;

import com.sun.tools.javac.Main;

import java.awt.*;
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

public class NewApp {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        boolean appRunning = true;
        loadTransactions(transactions);

        while (appRunning) {

            System.out.println("Welcome to Joi's Buisness Budget Book!\n");
            System.out.println("Please select from the following menu options:\n" +
                    "To enter a deposit, please enter (D)\n" +
                    "To log a payment, please enter (P)\n" +
                    "To check your account balance and transaction history, please enter (B)\n" +
                    "To exit, please enter (X)\n");

            String userSelection = scanner.nextLine().toUpperCase();
            switch (userSelection) {
                case "D":
                    displayDepositScreen();
                    break;
                case "P":
                    displayPaymentScreen();
                    break;
                case "B":
                    displayLedgerScreen();
                    break;
                case "X":
                    appRunning = false;
                    System.out.println("Thank you for using my budget book, Enjoi your day !");
                    break;
            }
        }
    }



//                                              - END OF MAIN -


    private static void displayDepositScreen() {

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
        saveTransaction(t);


        System.out.println("\n".repeat(10));
        System.out.println("Deposit Completed on " + date + " at " + time + "\n");
        System.out.println("\nPress Enter to return to the Main Menu...");
        scanner.nextLine();

    }

    private static void displayPaymentScreen() {

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
        scanner.nextLine();
        amt = amt * -1;


        Transaction t = new Transaction(date, time, description, vendor, amt);
        transactions.add(t);
        saveTransaction(t);

        System.out.println("\n".repeat(10));
        System.out.println("Payment Completed and Logged on " + date + " at " + time + "\n");

        System.out.println("\nPress Enter to return to the Main Menu...");
        scanner.nextLine();
    }


    private static void displayLedgerScreen() {
        boolean inLedger = true;
        double totalBalance = 0;
        for (Transaction t : transactions) {
            totalBalance += t.getAmount();
        }
        System.out.printf("\n CURRENT ACCOUNT BALANCE: $%.2f\n", totalBalance);

        while (inLedger) {
            System.out.println("\n View Account Details");
            System.out.println("A) All | D) Deposits | P) Payments | R) Reports | H) Home");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {

                case "A":
                    Transaction.displayAll(transactions);
                    break;
                case "D":
                    Transaction.displayDeposits(transactions);
                    break;
                case "P":
                    Transaction.displayPayments(transactions);
                    break;
                case "R":
                    Transaction.runReportsMenu(transactions, scanner);
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("And I Oop! Pick a valid letter.");
            }


        }
    }
    public static void loadTransactions(ArrayList<Transaction> transactions) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.toLowerCase().contains("date")) {
                    continue;
                }

                String[] parts = line.split("\\|");

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
    public static String getCurrentTimestamp() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date.format(dateFormatter) + "|" + time.format(timeFormatter);
    }








}