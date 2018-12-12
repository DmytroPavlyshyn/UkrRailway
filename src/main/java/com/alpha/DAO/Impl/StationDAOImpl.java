package com.alpha.DAO.Impl;

import com.alpha.DAO.BaseDAO;
import com.alpha.DAO.StationDAO;
import com.alpha.Entity.Station;
import com.alpha.config.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StationDAOImpl implements BaseDAO<Station>, StationDAO {
    @Override
    public Station getById(int Id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Station> carriages = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Station AS s WHERE s.id = ?");
            query.setParameter(0, Id);
            carriages = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        Optional<Station> first = carriages.stream().findFirst();
        return first.orElse(null);
    }

    @Override
    public boolean add(Station station) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(station);
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
            Query query = session.createQuery("DELETE FROM Station AS s WHERE s.id = ?");
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
    public List<Station> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Station> list = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Station AS s ORDER BY s.id");
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
