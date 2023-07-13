package org.ascending.project.repository;

import org.ascending.project.model.Customer;
import org.ascending.project.model.Insurance;
import org.ascending.project.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerHibernateDaoImpl implements ICustomerDao{

    private static final Logger logger = LoggerFactory.getLogger(CustomerHibernateDaoImpl.class);

    @Override
    public void save(Customer customer){

        logger.info("Start to save Customer to Postgres via Hibernate");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try{

            Session session = sessionFactory.openSession();
            session.save(customer);
            session.close();

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
        }
        logger.info("Have already saved {}", customer);
    }

    @Override
    public List<Customer> getCustomers(){

        logger.info("Start to getCustomer from Postgres via Hibernate");

        List<Customer> customers = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try{

            Session session = sessionFactory.openSession();

            String hql = "from Customer";
            Query<Customer> query = session.createQuery(hql);

            customers = query.list();
            session.close();

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
        }
        logger.info("Get Customers {}", customers);
        return customers;
    }

    @Override
    public Customer getById(long customer_id){
        logger.info("Start to getCustomer from Postgres via Hibernate");
        Customer customer = null;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try{

            Session session = sessionFactory.openSession();

            customer = session.get(Customer.class, customer_id);
            customer.setAge(Integer.valueOf("age"));
//            customer.setCarId(Long.parseLong("car_id"));
            customer.setFirstName("first_name");
            customer.setLastName("last_name");
            customer.setGender("gender");
            customer.setTransactionDate(Date.valueOf("transaction_date"));
            session.close();

        }catch (HibernateException e){
            logger.error("Unable to opan or close", e);
        }
        logger.info("Have already update {}", customer);
        return customer;
    }

    @Override
    public void delete(Customer customer){
        logger.info("Start to getCustomer from Postgres via Hibernate");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(customer);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
        }
        logger.info("Hava already delete {}", customer);
    }

}
