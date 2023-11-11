package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.model.User;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {

        return (User) entityManager.createQuery(
                        "SELECT u FROM User u  WHERE u.email LIKE :userEmail")
                .setParameter("userEmail", email).getSingleResult();


    }
}
