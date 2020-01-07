package dao;

import entity.Clients;
import entity.Stuff;

import java.util.List;

public interface StuffDao {

    void add(Stuff stuff);

    List<Stuff> getAll();


    void update(Stuff newStuff,Stuff oldStuff);

    void delete(Stuff stuff);




}
