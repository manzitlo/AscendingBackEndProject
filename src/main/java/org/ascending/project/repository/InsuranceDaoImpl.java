package org.ascending.project.repository;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.ascending.project.model.Insurance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class InsuranceDaoImpl implements IInsuranceDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5431/Car_DB";
    static final String USER = "wenzhe";
    static final String PASS = "Wenzhe7777";

    private Cache<String, List<Insurance>> cache;
    public InsuranceDaoImpl() {
        // Create cache instance based on Guava
        cache = CacheBuilder.newBuilder()
                .maximumSize(100) // max => 100...
                .build();
    }

    @Override
    public List<Insurance> getInsurances() {

        Logger logger = LoggerFactory.getLogger(getClass());
        logger.debug("Start to getInsurances from PostgresSQL via JDBC...");

        // Trying to get result from cache at first
        String cacheKey = "insurances";
        List<Insurance> cachedResult = cache.getIfPresent(cacheKey);
        if (cachedResult != null) {
            logger.info("Retrieved insurances from cache.");
            return cachedResult;
        }

        // if nothing, then check from DB
        Preconditions.checkNotNull(DB_URL, "DB_URL must not be null");
        Preconditions.checkNotNull(USER, "USER must not be null");
        Preconditions.checkNotNull(PASS, "PASS must not be null");


        //Step 1 : Prepare the required data model
        List<Insurance> insurances = new ArrayList<Insurance>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Step 2 : Open a connection => 5 key points to connect db
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3 : Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM insurances";
            rs = stmt.executeQuery(sql);
            logger.info("Connects to DB and execute the Query!");

            while (rs.next()){
                Long id = rs.getLong("id");
                String company_name = rs.getString("company_name");
                String Sepcifications = rs.getString("Sepcifications");

                Insurance insurance = new Insurance();
                insurance.setId(id);
                insurance.setCompanyName(company_name);
                insurance.setSepcifications(Sepcifications);
                insurances.add(insurance);
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

        cache.put(cacheKey, insurances);

        logger.info("Finish getInsurance", insurances);
        return insurances;
    }
}
