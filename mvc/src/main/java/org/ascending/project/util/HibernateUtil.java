package org.ascending.project.util;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    public static SessionFactory getSessionFactory() {
        logger.info("Start get session factory.");

        if (sessionFactory == null) {
            try {

                String[] modelPackages = {"org.ascending.project.model"}; // right address
                String dbDriver = "org.postgresql.Driver";
                String dbDialect = "org.hibernate.dialect.PostgreSQL9Dialect";
                String dbUrl = "jdbc:postgresql://localhost:5431/Car_DB";
                String dbUser = "wenzhe";
                String dbPassword = "Wenzhe7777";

                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, dbDriver);
                settings.put(Environment.DIALECT, dbDialect);
                settings.put(Environment.URL, dbUrl);
                settings.put(Environment.USER, dbUser);
                settings.put(Environment.PASS, dbPassword);
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "validate");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                configuration.setProperties(settings);

                EntityScanner.scanPackages(modelPackages).addTo(configuration);
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                ServiceRegistry serviceRegistry = registryBuilder.applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e){
                logger.error("Unable to create sessionFactory", e);
            }
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        try {
            SessionFactory sf = getSessionFactory();
            logger.info("Successfully generated sessionFactory, {}", sf.hashCode());
            Session s = sf.openSession();
            logger.info("Got a session to connect to the database");
            s.close();
        } catch (Exception e) {
            logger.error("Error in main method", e);
        }
    }


}

