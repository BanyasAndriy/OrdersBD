package service;

import dao.OrdersDao;
import entity.Clients;
import entity.Orders;
import entity.Stuff;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends Utils implements OrdersDao {

    private Connection connection = getConnection();

    @Override
    public void add(Orders order, Clients client, Stuff stuff) {

        if (!orderAlreadyExist(order)){
            String sql=" Insert into Orders (id_orders,id_client,id_stuff) values (?,?,?)";
            try (PreparedStatement pr = connection.prepareStatement(sql)) {

                pr.setInt(1,order.getOrderID());
                pr.setInt(2,client.getId());
                pr.setInt(3,stuff.getStuffId());
                pr.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();

            }}else {
            System.out.println("Такий заказ вже існує!!!!");
        }



    }

    @Override
    public List<Orders> getAll() {

        List<Orders> list = new ArrayList<>();

        String sql = "Select * from Orders";
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Orders order = new Orders();
                order.setOrderID(resultSet.getInt("id_orders"));


                order.setClientId(resultSet.getInt("id_client"));

                order.setStuffId(resultSet.getString("id_stuff"));


                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Orders newOrders, Orders oldOrders) {
        String sql = "Update Orders Set id_order=? , id_client=? , id_stuff=? where id_order=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,newOrders.getOrderID());
            preparedStatement.setInt(2,newOrders.getClientId());
            preparedStatement.setInt(3,newOrders.getStuffId());
            preparedStatement.setInt(4,oldOrders.getOrderID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Orders orders) {

        String sql = "Delete From Orders where id_order=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,orders.getOrderID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean orderAlreadyExist(Orders orders){

        boolean result = false;
        List<Orders> orders1 = new ArrayList<>();

        orders1=getAll();

        for (Orders cl: orders1) {
            if (cl.getOrderID()==orders.getOrderID() && cl.getClientId()==orders.getClientId() && cl.getStuffId()==orders.getStuffId() )
                result = true;
        }

        return result;
    }

}
