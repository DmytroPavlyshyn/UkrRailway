package com.alpha;


import com.alpha.config.HibernateUtil;
import org.hibernate.SessionFactory;

public class UkrRailway {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }
}
