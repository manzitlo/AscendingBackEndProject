package org.ascending.project.repository;

import org.ascending.project.model.University;
import org.ascending.project.repository.interfaces.IUniversityDao;
import org.ascending.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UniversityHibernateDaoMockTest {

    @Mock
    private SessionFactory mockSessionFactory;
    @Mock
    private Session mockSession;
    @Mock
    private Query<University> mockQuery;

    @Before
    public void setUp(){
        initMocks(this);
        IUniversityDao IUniversityDao = new UniversityHibernateDaoImpl();
    }

    
    @Test
    public void getUniversitiesTest_happyPath(){

        UniversityHibernateDaoImpl universityHibernateDao = new UniversityHibernateDaoImpl();

        University university = new University(1, "George Mason University", "Fairfax Virginia", "A public university which is located in VA", 100, "www.gmu.edu");
        List<University> result = List.of(university);

        try (MockedStatic<HibernateUtil> mockedStatic = mockStatic(HibernateUtil.class)){

            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);
            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(any(String.class), eq(University.class))).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(result);
            doNothing().when(mockSession).close();

            List<University> actualResult = universityHibernateDao.getUniversities();
            assertEquals(result, actualResult);
        }
    }

}
