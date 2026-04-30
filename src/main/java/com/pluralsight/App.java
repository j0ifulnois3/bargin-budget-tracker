//package com.pluralsight;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.FileReader;
//
//public class App {
//
//
//    static Scanner scanner = new Scanner(System.in);
//    static ArrayList<Transaction> transactions = new ArrayList<>();
//
//    public static void main(String[] args) {
//
//        boolean appRunning = true;
//        loadTransactions(transactions);
//
//        while (appRunning) {
//
//            System.out.println("Welcome to Joi's Buisness Budget Book!\n");
//            System.out.println("Please select from the following menu options:\n" +
//                    "To enter a deposit, please enter (D)\n" +
//                    "To log a payment, please enter (P)\n" +
//                    "To check your account balance and transaction history, please enter (B)\n" +
//                    "To exit, please enter (X)\n");
//
//            String userSelection = scanner.nextLine().toUpperCase();
//
//            switch (userSelection) {
//                case "D": {
//                    String timestamp = getCurrentTimestamp();
//                    String[] parts = timestamp.split("\\|");
//                    String date = parts[0];
//                    String time = parts[1];
//
//                    System.out.println("Apply Deposit Description:");
//                    String description = scanner.nextLine();
//
//                    System.out.println("Enter Deposit Origin:");
//                    String vendor = scanner.nextLine();
//
//                    System.out.println("Enter Deposit Amount:");
//                    double amt = scanner.nextDouble();
//                    scanner.nextLine();
//
//                    Transaction t = new Transaction(date, time, description, vendor, amt);
//                    transactions.add(t);
//                    saveTransaction(t);
//
//                    System.out.println("\n".repeat(10));
//                    System.out.println("Deposit Completed on " + date + " at " + time + "\n");
//                }
//
//                System.out.println("\nPress Enter to return to the Main Menu...");
//                scanner.nextLine();
//                break;
//
//                case "P": {
//                    String timestamp = getCurrentTimestamp();
//                    String[] parts = timestamp.split("\\|");
//                    String date = parts[0];
//                    String time = parts[1];
//
//                    System.out.println("Apply Payment Description:");
//                    String description = scanner.nextLine();
//
//                    System.out.println("Enter Payment Recipient:");
//                    String vendor = scanner.nextLine();
//
//                    System.out.println("Enter Payment Amount:");
//                    double amt = scanner.nextDouble();
//                    amt = amt * -1;
//                    scanner.nextLine();
//
//                    Transaction t = new Transaction(date, time, description, vendor, amt);
//                    transactions.add(t);
//                    saveTransaction(t);
//
//                    System.out.println("\n".repeat(10));
//                    System.out.println("Payment Completed and Logged on " + date + " at " + time + "\n");
//                }
//
//                System.out.println("\nPress Enter to return to the Main Menu...");
//                scanner.nextLine();
//                break;
//                case "B":{
////                    boolean inLedger = true;
////                    double totalBalance = 0;
////                    for (Transaction t : transactions) {
////                        totalBalance += t.getAmount();
////                    }
////                    System.out.printf("\n💰 CURRENT ACCOUNT BALANCE: $%.2f\n", totalBalance);
////                    while (inLedger) {
////                        System.out.println("\n View Account Details");
////                        System.out.println("A) All | D) Deposits | P) Payments | R) Reports | H) Home");
////                        String choice = scanner.nextLine().toUpperCase();
////
////                        switch (choice) {
////
////                            case "A": displayAll(transactions); break;
////                            case "D": displayDeposits(transactions); break;
////                            case "P": displayPayments(transactions); break;
////                            case "R": runReportsMenu(transactions, scanner); break;
////                            case "H": inLedger = false; break;
////                            default: System.out.println("And I Oop! Pick a valid letter.");
//                        }
//                    }
//                    break;
//                }
//
//                case "X":
//                    System.out.println("\n".repeat(10));
//                    System.out.println("Thank you for using Joi's Bargain Budget Book! Goodbye.");
//                    appRunning = false;
//                    break;
//                default:
//                    System.out.println("\n".repeat(10));
//                    System.out.println("And I Oop, sorry I dont know what you're talking, ");
//            }
//
//        }
//
//    }
//
//    static void runReportsMenu(ArrayList<Transaction> transactions, Scanner scanner) {
//        boolean inReports = true;
//        while (inReports) {
//            System.out.println("\n--- Reports ---");
//            System.out.println("1) Month To Date");
//            System.out.println("2) Previous Month");
//            System.out.println("3) Year To Date");
//            System.out.println("4) Previous Year");
//            System.out.println("5) Search by Vendor");
//            System.out.println("0) Back");
//
//            System.out.print("Select a report: ");
//            String choice = scanner.nextLine();
//            LocalDate today = LocalDate.now();
//
//            switch (choice) {
//                case "1": // Month to Date
//                    System.out.println("\n--- Month to Date ---");
//                    for (Transaction t : transactions) {
//                        LocalDate tDate = LocalDate.parse(t.getDate());
//                        if (tDate.getMonthValue() == today.getMonthValue() && tDate.getYear() == today.getYear()) {
//                            displayTransaction(t);
//                        }
//                    }
//                    break;
//
//                case "2": // Previous Month
//                    System.out.println("\n--- Previous Month ---");
//                    LocalDate lastMonth = today.minusMonths(1);
//                    for (Transaction t : transactions) {
//                        LocalDate tDate = LocalDate.parse(t.getDate());
//                        if (tDate.getMonthValue() == lastMonth.getMonthValue() && tDate.getYear() == lastMonth.getYear()) {
//                            displayTransaction(t);
//                        }
//                    }
//                    break;
//
//                case "5": // Search by Vendor
//                    System.out.print("Enter Vendor Name: ");
//                    String search = scanner.nextLine();
//                    System.out.println("\n--- Results for: " + search + " ---");
//                    for (Transaction t : transactions) {
//                        if (t.getVendor().equalsIgnoreCase(search)) {
//                            displayTransaction(t);
//                        }
//                    }
//                    break;
//
//                case "0":
//                    inReports = false;
//                    break;
//
//                default:
//                    System.out.println("And I Oop! Not a valid report option.");
//            }
//        }
//    }
//
//    private static void displayTransaction(Transaction t) {
//        System.out.printf("%s | %s | %-20s | %-15s | $%.2f\n",
//                t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
//    }
//
//
//
//    static void displayPayments(ArrayList<Transaction> transactions) {
//        System.out.println("\n All Deposits \n");
//        for (Transaction t: transactions){
//            if (t.getAmount()< 0){
//                System.out.println(t.getDate() + "|" + t.getDescription() + "| $" + t.getAmount());
//            }
//        }
//    }
//
//    static void displayDeposits(ArrayList<Transaction> transactions) {
//        System.out.println("\n All Deposits \n");
//        for (Transaction t: transactions){
//            if (t.getAmount()> 0){
//                System.out.println(t.getDate() + "|" + t.getDescription() + "| $" + t.getAmount());
//            }
//        }
//    }
//
//    public static String getCurrentTimestamp() {
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//        return date.format(dateFormatter) + "|" + time.format(timeFormatter);
//    }
//
//    public static void loadTransactions(ArrayList<Transaction> transactions) {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
//            String line;
//            while ((line = reader.readLine()) != null) {
//
//                if (line.toLowerCase().contains("date")) {
//                    continue;
//                }
//
//                String[] parts = line.split("\\|");
//
//                double amount = Double.parseDouble(parts[4]);
//                Transaction t = new Transaction(parts[0], parts[1], parts[2], parts[3], amount);
//                transactions.add(t);
//            }
//
//            reader.close();
//        } catch (IOException e) {
//            System.out.println("No previous history found. Starting a fresh book!");
//        }
//    }
//
//    public static void saveTransaction(Transaction t) {
//        try {
//
//            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));
//
//            String line = String.format("%s|%s|%s|%s|%.2f",
//                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
//
//            writer.write(line);
//            writer.newLine();
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("And I Oop! I couldn't save that record, girl.");
//        }
//    }
//
//    public static void displayAll(ArrayList<Transaction> transactions) {
//        System.out.println("\n--- Your Transaction History ---");
//
//
//        for (Transaction t : transactions) {
//            System.out.println(t.getDate() + " | " +
//                    t.getTime() + " | " +
//                    t.getDescription() + " | " +
//                    t.getVendor() + " | $" +
//                    t.getAmount());
//        }
//
//
//        if (transactions.isEmpty()) {
//            System.out.println("No transactions found. Your bag is empty!");
//        }
//    }
//
//
//
//
//
//
//}
//
//
//
//
