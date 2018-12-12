package com.alpha.DAO.Impl;

import com.alpha.DAO.BaseDAO;
import com.alpha.DAO.CarriageDAO;
import com.alpha.Entity.Carriage;
import com.alpha.config.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarriageDAOImpl implements BaseDAO<Carriage>, CarriageDAO {
    @Override
    public Carriage getById(int Id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Carriage> carriages = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Carriage AS c WHERE c.id = ?");
            query.setParameter(0, Id);
            carriages = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        Optional<Carriage> first = carriages.stream().findFirst();
        return first.orElse(null);
    }

    @Override
    public boolean add(Carriage carriage) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            session.beginTransaction();
            session.save(carriage);
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
            Query query = session.createQuery("DELETE FROM Carriage AS c WHERE c.id =?");
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
    public List<Carriage> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Carriage> list = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Carriage AS c ORDER BY c.id");
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
