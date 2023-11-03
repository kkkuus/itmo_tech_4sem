package repository.hibernate;

import dao.CatDao;
import database.HibernateUtil;
import entities.Cat;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CatHibernateService implements CatDao{

    public Cat save(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cat);
        transaction.commit();
        session.close();
        return cat;
    }

    public void deleteById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Cat cat = session.load(Cat.class, id);
        session.delete(cat);
        transaction.commit();
        session.close();
    }

    public void deleteByEntity(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Cat temp_cat = session.load(Cat.class, cat.getId());
        session.delete(temp_cat);
        transaction.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Cat")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Cat update(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cat);
        transaction.commit();
        session.close();
        return cat;
    }

    public Cat getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Cat cat = (Cat) session.get(Cat.class, id);
        session.getTransaction().commit();
        session.close();
        return cat;
    }

    public ArrayList<Cat> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ArrayList<Cat> cats = (ArrayList<Cat>) HibernateUtil.getSessionFactory().openSession().createQuery("FROM Cat").list();
        session.getTransaction().commit();
        session.close();
        return cats;
    }
}
