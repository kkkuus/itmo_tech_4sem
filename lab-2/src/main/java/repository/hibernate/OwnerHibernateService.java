package repository.hibernate;

import dao.OwnerDao;
import database.HibernateUtil;
import entities.Cat;
import entities.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;

public class OwnerHibernateService implements OwnerDao {
    public Owner save(Owner owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        session.close();
        return owner;
    }

    public void deleteById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Owner owner = session.load(Owner.class, id);
        session.delete(owner);
        transaction.commit();
        session.close();
    }

    public void deleteByEntity(Owner owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Owner temp_owner = session.load(Owner.class, owner.getId());
        session.delete(temp_owner);
        transaction.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //session.createQuery("DELETE FROM Cat ");
        session.createQuery("DELETE FROM Owner")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Owner update(Owner owner) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(owner);
        transaction.commit();
        session.close();
        return owner;
    }

    public Owner getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Owner owner = (Owner) session.get(Owner.class, id);
        session.getTransaction().commit();
        session.close();
        return owner;
    }

    public ArrayList<Owner> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ArrayList<Owner> owners = (ArrayList<Owner>) HibernateUtil.getSessionFactory().openSession().createQuery("FROM Owner").list();
        session.getTransaction().commit();
        session.close();
        return owners;
    }

    public ArrayList<Cat> getAllByOwnerId(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ArrayList<Cat> resultList =(ArrayList<Cat>) session.createQuery("FROM Cat WHERE Cat.owner = :id")
                .setParameter("id", id).getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }
}
