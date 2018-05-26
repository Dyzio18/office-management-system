package com.example.service;

import com.example.model.Service;

import java.util.List;

public interface Services {
    public List<Service> listService();
    public boolean delete(Service services);
    public boolean saveOrUpdate(Service services);
}
