package com.example.dao;

import com.example.model.Service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class ServiceDAOImpl implements ServiceDAO {

    @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public List<Service> listService() {
        return getSession().createQuery("from Services").list();
    }

    @Override
    public boolean saveOrUpdate(Service services) {
        getSession().saveOrUpdate(services);
        return true;
    }

    @Override
    public boolean delete(Service services) {
        try {
            getSession().delete(services);
        }
        catch(Exception ex) {
            return false;
        }
        return true;
    }
}
