package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public static void displayAll(ArrayList<Transaction> transactions) {
    }

    public static void displayDeposits(ArrayList<Transaction> transactions) {
    }

    public static void displayPayments(ArrayList<Transaction> transactions) {
    }

    public static void runReportsMenu(ArrayList<Transaction> transactions, Scanner scanner) {
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
        this.description= description;
        this.vendor = vendor;
        this.amount = amount;


    }



}
