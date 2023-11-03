package database;


import entities.Cat;
import entities.Owner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory;
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Cat.class);
            configuration.addAnnotatedClass(Owner.class);
            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
