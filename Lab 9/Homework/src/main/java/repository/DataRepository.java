package repository;

import entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import javax.transaction.Transactional;
import javax.persistence.EntityTransaction;

public abstract class DataRepository
        <T extends AbstractEntity, ID extends Serializable> {
    private EntityManager em;

    public DataRepository() {

    }

    public DataRepository(EntityManager em) {
        this.em = em;
    }

    public T findById(ID id) {
        return em.find(getEntityClass(), id);
    }

    protected abstract Class<T> getEntityClass();


    public boolean persist(T entity) {
        try {
            beginTransaction();
            em.persist(entity);
            commit();
            return true;
        } catch (Exception e) {
            handleException(e);
            rollback();
            return false;
        }

    }

    public void create(T entity){
        em.persist(entity);
    }

    public abstract T findById(Integer id);


    private void rollback() {
        EntityTransaction transaction = em.getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }

    private void handleException(Exception e) {
        e.printStackTrace();
    }

    private void commit() {
        EntityTransaction transaction = em.getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }

    private void beginTransaction() {
        EntityTransaction transaction = em.getTransaction();
        if (transaction != null && !transaction.isActive()) {
            transaction.begin();
        }
    }


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }


}