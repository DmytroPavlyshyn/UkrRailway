package com.alpha.DAO.Impl;

import com.alpha.DAO.BaseDAO;
import com.alpha.DAO.TicketDAO;
import com.alpha.Entity.Ticket;
import com.alpha.config.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TicketDAOImpl implements BaseDAO<Ticket>, TicketDAO {
    @Override
    public Ticket getById(int id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ticket> carriages = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Ticket AS t WHERE t.id = ?");
            query.setParameter(0, id);
            carriages = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        Optional<Ticket> first = carriages.stream().findFirst();
        return first.orElse(null);
    }

    @Override
    public boolean add(Ticket ticket) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(ticket);
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
            Query query = session.createQuery("DELETE FROM Ticket AS t WHERE t.id = ?");
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
    public List<Ticket> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ticket> list = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Ticket AS t ORDER BY t.id");
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
