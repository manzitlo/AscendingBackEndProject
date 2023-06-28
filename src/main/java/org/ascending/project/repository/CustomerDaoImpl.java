package org.ascending.project.repository;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.ascending.project.model.Customer;
import org.ascending.project.model.Insurance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl{

    static final String DB_URL = "jdbc:postgresql://localhost:5431/Car_DB";
    static final String USER = "wenzhe";
    static final String PASS = "Wenzhe7777";

    private Cache<String, List<Customer>> cache;
    public CustomerDaoImpl() {
        // Create cache instance based on Guava
        cache = CacheBuilder.newBuilder()
                .maximumSize(100) // max => 100...
                .build();
    }

    public List<Customer> getCustomers() {

        Logger logger = LoggerFactory.getLogger(getClass());

        logger.debug("Start to getCustomers from PostgresSQL via JDBC...");

        // Trying to get result from cache at first
        String cacheKey = "customers";
        List<Customer> cachedResult = cache.getIfPresent(cacheKey);
        if (cachedResult != null) {
            logger.info("Retrieved customers from cache.");
            return cachedResult;
        }

        // if nothing, then check from DB
        Preconditions.checkNotNull(DB_URL, "DB_URL must not be null");
        Preconditions.checkNotNull(USER, "USER must not be null");
        Preconditions.checkNotNull(PASS, "PASS must not be null");

        //Step 1 : Prepare the required data model
        List<Customer> customers = new ArrayList<Customer>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;


        try {
            //Step 2 : Open a connection => 5 key points to connect db
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3 : Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customers";
            rs = stmt.executeQuery(sql);
            logger.info("Connects to DB and execute the Query!");

            while (rs.next()){
                Long customer_id = rs.getLong("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                Integer age = rs.getInt("age");
                String gender = rs.getString("gender");
                Date transaction_date = rs.getDate("transaction_date");
                Long car_id = rs.getLong("car_id");

                Customer customer = new Customer();
                customer.setCustomerId(customer_id);
                customer.setFirstName(first_name);
                customer.setLastName(last_name);
                customer.setAge(age);
                customer.setGender(gender);
                customer.setTransactionDate(transaction_date);
                customer.setCarId(car_id);
                customers.add(customer);
            }


        } catch (SQLException e){
            logger.error("Unable to connect DB or execute Query.");
            //e.printStackTrace();
        } finally {

            // Step 6 : finally block used to close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e){
               logger.error("Unable to close DB connection.");
                //e.printStackTrace();
            }
        }

        cache.put(cacheKey, customers);

        logger.info("Finish getCustomer", customers);
        return customers;
    }
}
