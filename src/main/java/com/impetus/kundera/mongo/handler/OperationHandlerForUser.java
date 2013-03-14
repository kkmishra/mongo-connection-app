/**
 * 
 */
package com.impetus.kundera.mongo.handler;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.impetus.kundera.mongo.entities.User;

/**
 * @author impadmin
 * 
 */
public class OperationHandlerForUser implements OperationHandler
{

    private EntityManagerFactory emf;

    private EntityManager em;

    @Override
    public void saveUser(User user)
    {
        getEntityManager();
        em.persist(user);
    }

    @Override
    public User findUserById(Class clazz, Object id)
    {
        getEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public List<User> findUserByName(String clazz, String name)
    {

        getEntityManager();
        Query q = em.createQuery("Select u from " + clazz + " u " + "where u.name = " + name);
        return q.getResultList();

    }

    @Override
    public List<User> findUserByAge(String clazz, int age)
    {
        getEntityManager();
        Query q = em.createQuery("Select u from " + clazz + " u " + "where u.age = " + age);
        return q.getResultList();
    }

    @Override
    public List<User> findAllUser(String clazz)
    {
        getEntityManager();
        Query q = em.createQuery("Select u from " + clazz + " u ");
        return q.getResultList();
    }

    private EntityManager getEntityManager()
    {

        if (emf == null)
        {
            emf = Persistence.createEntityManagerFactory("mongo-openshift_pu,rdbms-openshift_pu");
            em = emf.createEntityManager();

        }
        else if (em == null)
        {
            em = emf.createEntityManager();
        }
        return em;
    }

    public void removeAll(String clazz)
    {
        getEntityManager();
        Query q = em.createQuery("Delete from " + clazz + " u ");
    }

    public void close()
    {
        em.close();
        emf.close();
    }

    public void clear()
    {
        em.clear();
    }
}
