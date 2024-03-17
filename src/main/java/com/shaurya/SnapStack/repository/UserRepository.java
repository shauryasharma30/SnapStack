package com.shaurya.SnapStack.repository;

import com.shaurya.SnapStack.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "imageapp")
    private EntityManagerFactory entityManagerFactory;

    public void signupUser(User newUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newUser);
            transaction.commit();
        }
        catch(Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
    }

    public User checkCredentials(String username, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
    }
}
