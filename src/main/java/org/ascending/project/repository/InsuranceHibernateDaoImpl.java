package org.ascending.project.repository;

import org.ascending.project.model.Insurance;
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
public class InsuranceHibernateDaoImpl implements IInsuranceDao{

    private static final Logger logger = LoggerFactory.getLogger(InsuranceHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Insurance insurance){
        logger.info("Start to getInsurance from Postgres via Hibernate");

        Transaction transaction = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(insurance);
            transaction.commit();
            session.close();


        } catch (HibernateException e){
            if (transaction != null){
                logger.error("Save Transaction failed... Rollback ing.");
                transaction.rollback();
            }
            logger.error("Unable to open or close", e);
        }
        logger.info("Hava already save {}", insurance);
    }

    @Override
    public List<Insurance> getInsurances(){

        logger.info("Start to getInsurance from Postgres via Hibernate");

        List<Insurance> insurances = new ArrayList<>();

        try{
            Session session = sessionFactory.openSession();

            String hql = "from Insurance";
            Query<Insurance> query = session.createQuery(hql);

            insurances = query.list();
            session.close();

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
        }

        logger.info("Get insurances {}", insurances);
        return insurances;
    }

    @Override
    // Update
    public Insurance getById(long id) {
        logger.info("Start to getInsurance from Postgres via Hibernate");
        Insurance insurance = null;

        Transaction transaction = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            insurance = session.get(Insurance.class, id);
            insurance.setSepcifications("Sepcifications");
            insurance.setCompanyName("company_name");
            transaction.commit();
            session.close();

        } catch (HibernateException e){
            if (transaction != null) {
                logger.error("Update Transaction failed... Rollback ing.");
                transaction.rollback();
            }
            logger.error("Unable to open or close", e);
        }
        logger.info("Hava already update {}", insurance);
        return insurance;
    }

    @Override
    public void delete(Insurance insurance) {
        logger.info("Start to getInsurance from Postgres via Hibernate");

        Transaction transaction = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(insurance);
            transaction.commit();
            session.close();
            logger.debug("Removed insurance from cache: {}", insurance);

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
            if (transaction != null){
                logger.error("Delete Transaction failed... Rollback ing.");
                transaction.rollback();
            }
        }
        logger.info("Hava already delete {}", insurance);
    }

    @Override
    public Insurance update(Insurance insurance){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(insurance);
            Insurance i = getById(insurance.getId());
            transaction.commit();
            session.close();
            return i;
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
