package com.alpha.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory ;
    private static StandardServiceRegistry serviceRegistry;


    public static SessionFactory getSessionFactory(){
        Configuration cfg = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());

        sessionFactory =cfg.buildSessionFactory(builder.build());
        return sessionFactory;
    }
}
