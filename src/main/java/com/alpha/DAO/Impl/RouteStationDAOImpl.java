package com.alpha.DAO.Impl;

import com.alpha.DAO.BaseDAO;
import com.alpha.Entity.RouteStation;
import com.alpha.config.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RouteStationDAOImpl implements BaseDAO<RouteStation> {
    @Override
    public RouteStation getById(int Id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<RouteStation> carriages = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM RouteStation AS rs WHERE rs.id = ?");
            query.setParameter(0, Id);
            carriages = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        Optional<RouteStation> first = carriages.stream().findFirst();
        return first.orElse(null);
    }

    @Override
    public boolean add(RouteStation routeStation) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(routeStation);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM RouteStation AS rs WHERE rs.id = ?");
            query.setParameter(0, id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<RouteStation> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<RouteStation> list = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM RouteStation AS rs ORDER BY rs.id");
            list = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
