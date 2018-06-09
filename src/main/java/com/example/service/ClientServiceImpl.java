package com.example.service;

import com.example.model.Client;
import com.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List <Client> getAll(){
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer Id) {
        return clientRepository.findById(Id);
    }

    @Override
    public void saveClient(Client client){
        client.setC_name(client.getC_name());
        client.setC_surname(client.getC_surname());
        client.setC_phone(client.getC_phone());
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client){
        saveClient(client);
    }
}
