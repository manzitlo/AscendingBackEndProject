package org.ascending.project.repository;

import org.ascending.project.model.Car;
import org.ascending.project.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarHibernateDaoImpl implements ICarDao {
    public static final Logger logger = LoggerFactory.getLogger(CarHibernateDaoImpl.class);

    @Override
    public void save(Car car){
        logger.info("Start to getCar from Postgres via Hibernate");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
        }
        logger.info("Hava already save {}", car);
    }

    @Override
    public List<Car> getCars(){

        logger.info("Start to getCar from Postgres via Hibernate");

        List<Car> cars = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{

            String hql = "from Car";
            Query<Car> query = session.createQuery(hql);

            cars = query.list();
            session.close();

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
            session.close();
        }

        logger.info("Get cars {}", cars);
        return cars;
    }

    @Override
    // Update
    public Car getById(long id) {
        logger.info("Start to getCar from Postgres via Hibernate");
        Car car = null;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try{
            Session session = sessionFactory.openSession();

            car = session.get(Car.class, id);
            car.setBrand("brand");
            car.setYear(Integer.valueOf("year"));
            car.setModel("model");
            car.setColor("color");
//            car.setInsuranceId(Long.parseLong("insurance_id"));
            session.close();

        } catch (HibernateException e){
            logger.error("Unable to open or close", e);
        }
        logger.info("Hava already update {}", car);
        return car;
    }

    @Override
    public void delete(Car car) {
        logger.info("Start to getCar from Postgres via Hibernate");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
            session.close();

        } catch (HibernateException e){
            if (transaction != null){
                logger.error("Delete transaction failed, Rollback...");
                transaction.rollback();
            }
            logger.error("Unable to open or close", e);
        }
        logger.info("Hava already delete {}", car);
    }

//    @Override
//    public Car getCarEagerBy(Long id){
//        String hql = "FROM Car c LEFT JOIN FETCH c.customers where c.id :Id";
//        Session
//    }
}

