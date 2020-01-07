package dao;

import entity.Clients;

import java.util.List;

public interface ClientsDao {

    void add(Clients client);

    List<Clients> getAll();


    void update(Clients newClient,Clients oldClient);

    void delete(Clients client);



}
