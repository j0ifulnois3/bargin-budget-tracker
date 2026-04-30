package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;


    public static void displayAll(ArrayList<Transaction> transactions) {
        System.out.println("--- All Transactions ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (i == transactions.size() - 1) {
                i--;
            }
            {
                t = transactions.get(i);

                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }

        }
    }

    public static void displayDeposits(ArrayList<Transaction> transactions) {
        System.out.println("\n--- DEPOSIT HISTORY ---");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);


            if (t.getAmount() > 0) {
                System.out.printf("%s | %-15s | $%.2f\n",
                        t.getDate(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void displayPayments(ArrayList<Transaction> transactions) {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getAmount() < 0) {
                System.out.printf("%s | %-15s | $%.2f\n",
                        t.getDate(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void runReportsMenu(ArrayList<Transaction> transactions, Scanner scanner) {
        boolean reporting = true;
        while (reporting) {
            System.out.println("1) Month To Date\n2) Previous Month\n3) Year To Date\n4) Previous Year\n5) Search by Vendor\n0) Back");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    LocalDate today = LocalDate.now();
                    for (Transaction t : transactions) {
                        LocalDate recordDate = LocalDate.parse(t.getDate());
                        if (recordDate.getMonthValue() == today.getMonthValue() &&
                                recordDate.getYear() == today.getYear()) {
                            System.out.printf("%s | %s | %s | %s | $%.2f\n",
                                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

                        }
                    }
                    break;
                case "2":
                    LocalDate target = LocalDate.now().minusMonths(1);
                    System.out.println("--- Previous Month Report ---");
                    for (Transaction t : transactions) {
                        LocalDate recordDate = LocalDate.parse(t.getDate());
                        if (recordDate.getMonthValue() == target.getMonthValue() &&
                                recordDate.getYear() == target.getYear()) {
                            System.out.printf("%s | %-15s | $%.2f\n", t.getDate(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "3":
                    today = LocalDate.now();
                    for (Transaction t : transactions) {
                        LocalDate recordDate = LocalDate.parse(t.getDate());
                        if (recordDate.getYear() == today.getYear()) {
                            System.out.printf("%s | %s | %s | %s | $%.2f\n",
                                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

                        }
                    }
                    break;
                case "4":
                    int lastYear = LocalDate.now().minusYears(1).getYear();
                    System.out.println("--- Previous Year Report ---");
                    for (Transaction t : transactions) {
                        LocalDate recordDate = LocalDate.parse(t.getDate());
                        if (recordDate.getYear() == lastYear) {
                            System.out.printf("%s | %-15s | $%.2f\n", t.getDate(), t.getVendor(), t.getAmount());
                        }
                    }
                    break;
                case "5":
                    System.out.println("Enter Vendor Name:");
                    String search = scanner.nextLine();
                    for (Transaction t : transactions) {
                        if (t.getVendor().equalsIgnoreCase(search)) {
                            System.out.printf("%s | %s | %s | %s | $%.2f\n",
                                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    if (!found) {
                        System.out.println("\nAnd I Oop! I couldn't find any transactions for: " + search);
                        System.out.println("Redirecting you back to the Reports Menu...");
                    }

                    break;
                case "0":
                    reporting = false; // Sends you back to the Ledger menu
                    break;
                    if (!found){System.out.println("Incorrect Value. Please try again.");
                    }
            }
        }
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;


    }


}
