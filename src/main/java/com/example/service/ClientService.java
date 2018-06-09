package com.example.service;

import com.example.model.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getAll();
    public  void saveClient(Client client);
    public Client findById(Integer Id);
    public void updateClient(Client client);
}
