package repositories.UtilizatorRepository;

import domain.entities.Utilizator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UtilizatorRepositoryImpl implements UtilizatorRepository {
    private SessionFactory sessionFactory;

    public UtilizatorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Utilizator findOne(String uuid) {
        final Utilizator entity;
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        entity = (Utilizator) session.get(Utilizator.class.getName(), uuid);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    @Override
    public void save(Utilizator entity) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(String id) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        Utilizator entity;
        entity = (Utilizator) session.get(Utilizator.class.getName(), id);
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Utilizator entity, String id) {
        this.delete(id);
        this.save(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Utilizator> findAll() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        final List<Utilizator> list;
        list = (List<Utilizator>) session.createQuery("from " + Utilizator.class.getName()).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
