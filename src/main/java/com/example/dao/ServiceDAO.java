package com.example.dao;

import com.example.model.Service;

import java.util.List;

public interface ServiceDAO {
    public List<Service> listService();
    public boolean delete(Service services);
    public boolean saveOrUpdate(Service services);
}
