package com.ilp04.utility;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpa;

import java.util.Scanner;
import java.util.ArrayList;

public class CustomerUtility {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Insert Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Display All Customers");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after integer input

            switch (choice) {
                case 1:
                    insertCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    getAllCustomers();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        }

        scanner.close();
    }

    private static void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer Code:");
        int customerCode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter First Name:");
        String customerFirstname = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String customerLastname = scanner.nextLine();
        System.out.println("Enter Address:");
        String customerAddress = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        long phoneNumber = scanner.nextLong();
        System.out.println("Enter Aadhar Number:");
        long aadharNumber = scanner.nextLong();

        Customer customer = new Customer(customerCode, customerFirstname, customerLastname, customerAddress, phoneNumber, aadharNumber);
        CustomerService customerService = new CustomerServiceImpa();
        customerService.updateCustomer(customer);

        System.out.println("Customer updated successfully.");
    }

    private static void insertCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String customerFirstname = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String customerLastname = scanner.nextLine();
        System.out.println("Enter Address:");
        String customerAddress = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        long phoneNumber = scanner.nextLong();
        System.out.println("Enter Aadhar Number:");
        long aadharNumber = scanner.nextLong();

        Customer customer = new Customer(customerFirstname, customerLastname, customerAddress, phoneNumber, aadharNumber);
        CustomerService customerService = new CustomerServiceImpa();
        customerService.insertIntoCustomer(customer);

        System.out.println("Customer inserted successfully.");
    }

    private static void getAllCustomers() {
        CustomerService customerService = new CustomerServiceImpa();
        ArrayList<Customer> customerList = customerService.getAllCustomers();

        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("List of customers:");
            for (Customer customer : customerList) {
                System.out.println("Customer Code: " + customer.getCustomerCode());
                System.out.println("First Name: " + customer.getCustomerFirstname());
                System.out.println("Last Name: " + customer.getCustomerLastname());
                System.out.println("Address: " + customer.getAddress());
                System.out.println("Phone Number: " + customer.getPhNumber());
                System.out.println("Aadhar No: " + customer.getAadharNo());
                System.out.println("------------------------------");
            }
        }
    }

}
