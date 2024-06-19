package com.ilp04.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.ilp04.dao.CustomerDAO;
import com.ilp04.entity.Customer;

public class CustomerServiceImpa implements CustomerService {

	@Override
	public ArrayList<Customer> getAllCustomers() {
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		customerList = customerDAO.getAllCustomers(connection);
		return customerList;
		}

	@Override
	public void insertIntoCustomer(Customer customer) {
		// TODO Auto-generated method stub
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		customerDAO.insertIntoCustomer(connection, customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		customerDAO.updtadeCustomer(connection, customer);
	}
}
