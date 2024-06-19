package com.ilp04.dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp04.entity.Customer;
 
public class CustomerDAO {
	
	private ArrayList<Customer> customerList;

	//get connection
	public Connection getConnection()
	{
		String connectionURL="jdbc:mysql://localhost:3306/bankdb";
		String userName="root";
		String password="experion@123";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL,userName,password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	//close connection
	
	public Connection closeConnection(Connection connection) 
	{
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	//get all customer details
 
	public ArrayList<Customer> getAllCustomers(Connection connection){
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Statement statement;
		ResultSet resultSet;
		
		try 
		{
			statement  = connection.createStatement();
			resultSet = statement.executeQuery("select * from customer");
			while(resultSet.next()){
				int customerCode = resultSet.getInt(1);
				String customerFirstname = resultSet.getString(2);
				String customerLastname = resultSet.getString(3);
				String address = resultSet.getString(4);
				long phNumber = resultSet.getLong(5);
				long aadharNo = resultSet.getLong(6);
				Customer customer = new Customer(customerCode,customerFirstname,customerLastname,address,phNumber,aadharNo);
				customerList.add(customer);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	
	
	
	public ArrayList<Customer> insertIntoCustomer(Connection connection, Customer customer){
		PreparedStatement statement;
		int rowsInserted = 0;
		
		try 
		{
			String sql = "INSERT INTO customer (customer_firstname, customer_lastname, address, phonenumber, aadharnumber) VALUES (?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getCustomerFirstname());
            statement.setString(2, customer.getCustomerLastname());
            statement.setString(3, customer.getAddress());
            statement.setLong(4, customer.getPhNumber());
            statement.setLong(5, customer.getAadharNo());
            
            rowsInserted = statement.executeUpdate();
            if(rowsInserted>0) {
            	System.out.println("Customer entered Successfully");
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	
	public void updtadeCustomer(Connection connection, Customer customer){
		PreparedStatement statement;
		int rowsUpdated = 0;
		
		try {
	        String sql = "UPDATE customer SET customer_firstname = ?, customer_lastname = ?, address = ?, phonenumber = ?, aadharnumber = ? WHERE customer_code = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, customer.getCustomerFirstname());
	        statement.setString(2, customer.getCustomerLastname());
	        statement.setString(3, customer.getAddress());
	        statement.setLong(4, customer.getPhNumber());
	        statement.setLong(5, customer.getAadharNo());
	        statement.setInt(6, customer.getCustomerCode());

	        rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Customer updated successfully");
	        } else {
	            System.out.println("No customer found with customerCode: " + customer.getCustomerCode());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    }
	}