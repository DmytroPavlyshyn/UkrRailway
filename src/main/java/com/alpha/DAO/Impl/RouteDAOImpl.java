package com.alpha.DAO.Impl;

import com.alpha.DAO.BaseDAO;
import com.alpha.DAO.RouteDAO;
import com.alpha.Entity.Route;
import com.alpha.config.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RouteDAOImpl implements BaseDAO<Route>, RouteDAO {
    @Override
    public Route getById(int id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Route> carriages = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Route AS r WHERE r.id = ?");
            query.setParameter(0, id);
            carriages = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        Optional<Route> first = carriages.stream().findFirst();
        return first.orElse(null);
    }

    @Override
    public boolean add(Route route) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(route);
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
            Query query = session.createQuery("DELETE FROM Route AS r WHERE r.id = ?");
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
    public List<Route> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Route> list = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Route AS r ORDER BY r.id");
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
