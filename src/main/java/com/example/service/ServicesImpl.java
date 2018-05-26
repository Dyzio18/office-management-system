package com.example.service;

import com.example.dao.ServiceDAO;
import com.example.model.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServicesImpl implements Services {

    @Autowired
    ServiceDAO serviceDAO;

    @Override
    public List<Service> listService() {
        return serviceDAO.listService();
    }

    @Override
    public boolean delete(Service services) {
        return serviceDAO.delete(services);
    }

    @Override
    public boolean saveOrUpdate(Service services) {
        return serviceDAO.saveOrUpdate(services);
    }
}
