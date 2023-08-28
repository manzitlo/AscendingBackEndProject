package org.ascending.project.config;

import org.ascending.project.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.ascending.project.repository")
public class HibernateConfig {

    @Bean
    public SessionFactory getHibernateSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

}
