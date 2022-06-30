package com.andersen.repository;

import com.andersen.exception.FailedUpdate;
import com.andersen.model.Track;
import com.andersen.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.sql.Template;

import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public class TrackRepositoryImpl implements TrackRepository {
    private final SessionFactory sessionFactory;

    @Override
    public void delete(Track entity) {
        this.deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            return;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int rowsUpdated = session
                    .createQuery("delete from Track t where t.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            if (rowsUpdated == 0) {
                throw new FailedUpdate("Failed to delete entity with id -" + id);
            }
            transaction.commit();
        } catch (FailedUpdate e) {
            log.throwing(this.getClass().toString(), "deleteById", e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null)
            return false;
        try (Session session = sessionFactory.openSession()) {
            return session.get(Track.class, id) != null;
        }
    }

    @Override
    public List<Track> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select t from Track t", Track.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<Track> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(session.get(Track.class, id));
        }
    }

    @Override
    public Track getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.load(Track.class, id);
        }
    }

    @Override
    public Track save(Track entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            log.throwing(this.getClass().toString(), "save", e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Track> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select t from Track t where t.user = :user", Track.class)
                    .setParameter("user", user)
                    .getResultList();
        }
    }
}
