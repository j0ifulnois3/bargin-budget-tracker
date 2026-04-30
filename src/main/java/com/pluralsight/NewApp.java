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


    Scanner scanner = new Scanner(System.in);
    ArrayList<Transaction> transactions = new ArrayList<>();

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
            switch (command) {
                case "D":
                    displayDepositScreen();
                    break;
                Case "P":
                displayPaymentScreen();
                break;
                case "L":
                    displayLedgerScreen();
                    break;
                case "X":
                    programIsRunning = false;
                    break;
            }
        }
    }
    }
//                                              - END OF MAIN -
