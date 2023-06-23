package org.ascending.project.repository;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.ascending.project.model.Car;
import org.ascending.project.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements ICarDao{
    static final String DB_URL = "jdbc:postgresql://localhost:5431/Car_DB";
    static final String USER = "wenzhe";
    static final String PASS = "Wenzhe7777";

    private Cache<String, List<Car>> cache;
    public CarDaoImpl() {
        // Create cache instance based on Guava
        cache = CacheBuilder.newBuilder()
                .maximumSize(100) // max => 100...
                .build();
    }

    @Override
    public List<Car> getCars() {

        Logger logger = LoggerFactory.getLogger(getClass());

        logger.debug("Start to getCars from PostgresSQL via JDBC...");

        // Trying to get result from cache at first
        String cacheKey = "customers";
        List<Car> cachedResult = cache.getIfPresent(cacheKey);
        if (cachedResult != null) {
            logger.info("Retrieved customers from cache.");
            return cachedResult;
        }

        // if nothing, then check from DB
        Preconditions.checkNotNull(DB_URL, "DB_URL must not be null");
        Preconditions.checkNotNull(USER, "USER must not be null");
        Preconditions.checkNotNull(PASS, "PASS must not be null");

        //Step 1 : Prepare the required data model
        List<Car> cars = new ArrayList<Car>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Step 2 : Open a connection => 5 key points to connect db
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3 : Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM cars";
            rs = stmt.executeQuery(sql);
            logger.info("Connects to DB and execute the Query!");

            while (rs.next()){
                Long id = rs.getLong("id");
                String brand = rs.getString("brand");
                Integer year = rs.getInt("year");
                String model = rs.getString("model");
                Long insurance_id = rs.getLong("insurance_id");
                Long car_id = rs.getLong("car_id");

                Car car = new Car();
                car.setId(id);
                car.setBrand(brand);
                car.setYear(year);
                car.setModel(model);
                car.setInsuranceId(insurance_id);
                cars.add(car);
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

        cache.put(cacheKey, cars);

        logger.info("Finish getCar", cars);
        return cars;
    }
}
