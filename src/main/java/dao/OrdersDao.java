package dao;

import entity.Clients;
import entity.Orders;
import entity.Stuff;

import java.util.List;

public interface OrdersDao {


    void add(Orders order, Clients client , Stuff stuff);

    List<Orders> getAll();


    void update(Orders newOrders,Orders oldOrders);

    void delete(Orders orders);


}
