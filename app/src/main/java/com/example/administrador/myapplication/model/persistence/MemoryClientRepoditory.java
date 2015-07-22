package com.example.administrador.myapplication.model.persistence;

import com.example.administrador.myapplication.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class MemoryClientRepoditory implements ClientRepository {

    private static MemoryClientRepoditory singletonInstance;

    private List<Client> clients;

    private MemoryClientRepoditory() {
        super();
        clients = new ArrayList<Client>();
    }

    public static MemoryClientRepoditory getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new MemoryClientRepoditory();
        }
        return singletonInstance;
    }

    @Override
    public void save(Client client) {
        if(!clients.contains(client)){
            clients.add(client);
        }
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }
}
