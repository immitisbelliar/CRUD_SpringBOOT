package com.application.SpringBootCRUDApp.dao;


import com.application.SpringBootCRUDApp.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
        entityManager.flush();
    }

    @Override
    public void delete(int id) {
        User user = show(id);
        if (user == null) {
            throw new NullPointerException("User not found");
        }

        entityManager.remove(user);
        entityManager.flush();
    }
}
