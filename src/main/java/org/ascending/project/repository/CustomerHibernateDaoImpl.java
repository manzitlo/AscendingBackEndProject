package org.ascending.project.repository;

import org.ascending.project.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerHibernateDaoImpl implements ICustomerDao{

    private static final Logger logger = LoggerFactory.getLogger(CustomerHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Customer customer){

        logger.info("Start to save Customer to Postgres via Hibernate");

        Transaction transaction = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
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
    public Customer getById(long customerId){
        logger.info("Start to getCustomer from Postgres via Hibernate");
        Session session = sessionFactory.openSession();

        String hql = "FROM Customer c where id= :Id";

        try{

            Query<Customer> query = session.createQuery(hql);
            query.setParameter("Id", customerId);
            Customer result = query.uniqueResult();
            session.close();
            return result;

        }catch (HibernateException e){
            logger.error("Unable to open or close", e);
            session.close();
            return null;
        }
    }

    @Override
    public void delete(Customer customer){
        logger.info("Start to getCustomer from Postgres via Hibernate");

        Transaction transaction = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
            session.close();

        } catch (HibernateException e){
            if (transaction != null){
                logger.error("Delete transaction failed, Rollback...");
                transaction.rollback();
            }
            logger.error("Unable to open or close", e);
        }
        logger.info("Hava already delete {}", customer);

    }

    @Override
    public Customer update(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(customer);
            Customer c = getById(customer.getCustomerId());
            transaction.commit();
            session.close();
            return c;
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            logger.error("failed to insert record", e);
            session.close();
            return null;
        }
    }

}
