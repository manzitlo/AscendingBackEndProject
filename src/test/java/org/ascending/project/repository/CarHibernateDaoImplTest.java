package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.Car;
import org.ascending.project.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
@Transactional
public class CarHibernateDaoImplTest {

//    @Mock  // only includes function signature...
//    private SessionFactory mockSessionFactory;
//    @Mock
//    private Session mockSession;
//    @Mock
//    private Query mockQuery;
//
//    private ICarDao carDao;
//
//    @Before
//    public void setup() {
//        initMocks(this);
//        carDao = new CarHibernateDaoImpl();
//    }
//
//    @Test
//    public void getCarsTest_happyPath() {
//
//        carDao = mock(CarHibernateDaoImpl.class);
//
//        //Car(long id, String brand, long year, String model, String color, long insuranceId)
//        Car car = new Car(1,"BMW", 2023, "X7", "black", 61);
//        List<Car> result = List.of(car);
//
//        try (MockedStatic mockedStatic = mockStatic(HibernateUtil.class)) {
//            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);
//
//            when(mockSessionFactory.openSession()).thenReturn(mockSession);
//            when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
//            when(mockQuery.list()).thenReturn(result);
//            doNothing().when(mockSession).close();
//
//            List<Car> actualResult = carDao.getCars();
//            assertEquals(result, actualResult);
//        }
//    }
//
//    @Test
//    public void getCarsTest_getHibernateException() {
//
//        Car car = new Car(1,"BMW", 2023, "X7", "black", 61);
//        List<Car> result = List.of(car);
//
//        try (MockedStatic mockedStatic = mockStatic(HibernateUtil.class)) {
//            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);
//
//            when(mockSessionFactory.openSession()).thenReturn(mockSession);
//            when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
//            when(mockQuery.list()).thenReturn(result);
//            doThrow(HibernateException.class).when(mockSession).close();
//
//            assertThrows(HibernateException.class, () -> carDao.getCars());
//
//        }
//    }
//
//    @AfterClass
//    public static void tearDown() {
//        HibernateUtil.getSessionFactory().close();
//    }


//    JUnit Testing:
    @Autowired
    private CarHibernateDaoImpl carHibernateDao;
    @Autowired
    private CustomerHibernateDaoImpl customerHibernateDao;
    private Car c1;
    private Car c2;
    private Customer cu1;
    private Customer cu2;
    private Customer cu3;

    @Before
    public void setUp(){
        c1 = new Car();
        c1.setBrand("Benz");
        c1.setModel("GL450");
        c1.setYear(2014);
        c1.setColor("black");
        c1.setInsuranceId(1);
        carHibernateDao.save(c1);

        c2 = new Car();
        c2.setBrand("Honda");
        c2.setModel("Passport");
        c2.setYear(2023);
        c2.setColor("grey");
        c2.setInsuranceId(2);
        carHibernateDao.save(c2);

        cu1 = new Customer();
        cu1.setAge(20);
        cu1.setFirstName("Wesley");
        cu1.setLastName("Ron");
        cu1.setGender("Male");
        cu1.setTransactionDate(Date.valueOf("2020-02-10"));
        cu1.setCar(c1);
        customerHibernateDao.save(cu1);

        cu2 = new Customer();
        cu2.setAge(18);
        cu2.setFirstName("Iris");
        cu2.setLastName("Nancy");
        cu2.setGender("Female");
        cu2.setTransactionDate(Date.valueOf("2023-02-10"));
        cu2.setCar(c2);
        customerHibernateDao.save(cu2);

        cu3 = new Customer();
        cu3.setAge(3);
        cu3.setFirstName("Tracy");
        cu3.setLastName("DOXIE");
        cu3.setGender("Female");
        cu3.setTransactionDate(Date.valueOf("2023-02-10"));
        cu3.setCar(c2);
        customerHibernateDao.save(cu3);
    }

    @After
    public void teardown() {
//        carHibernateDao.delete(c1);
    }

    @Test
    public void getCarTest() {
        assertEquals(2, carHibernateDao.getCars().size());
    }


}