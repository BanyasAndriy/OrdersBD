package service;


import com.mysql.cj.x.protobuf.MysqlxPrepare;
import dao.ClientsDao;
import entity.Clients;
import org.omg.CosNaming._BindingIteratorImplBase;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService extends Utils implements ClientsDao {

private Connection connection = getConnection();

    @Override
    public void add(Clients client) {

        if (!clientAlreadyExist(client)){
      String sql="Insert into Clients (pib,phone,address) values (?,?,?)";
        try (PreparedStatement pr = connection.prepareStatement(sql)) {

            pr.setString(1,client.getPib());
            pr.setString(2,client.getPhone());
            pr.setString(3,client.getAddress());
            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }}else {
            System.out.println("Такий клієнт вже існує!!!!");
        }
        client.setId(getIdByPhone(client));



    }

    @Override
    public List<Clients> getAll() {
List<Clients> list = new ArrayList<>();

        String sql = "Select * from Clients";
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Clients client = new Clients();
                 client.setId(resultSet.getInt("id_client"));
                 client.setPib(resultSet.getString("pib"));
                 client.setPhone(resultSet.getString("phone"));
                 client.setAddress(resultSet.getString("address"));

                 list.add(client);
        }
        } catch (SQLException e) {
            e.printStackTrace();

        }
return list;
    }



    @Override
    public void update(Clients newClient,Clients oldClient) {

String sql = "Update Clients Set pib=? , phone=? , address=? where phone=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,newClient.getPib());
            preparedStatement.setString(2,newClient.getPhone());
            preparedStatement.setString(3,newClient.getAddress());
            preparedStatement.setString(4,oldClient.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Clients client) {


        String sql = "Delete From Clients where phone=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
preparedStatement.setString(1,client.getPhone());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean clientAlreadyExist(Clients client){

        boolean result = false;
        List<Clients> clients = new ArrayList<>();

        clients=getAll();

        for (Clients cl: clients) {
            if (cl.getPhone().equals(client.getPhone()))
            result = true;
        }

        return result;
    }


    public int getIdByPhone(Clients client){
        int res=0;
        String sql1 = "Select id_client From Clients Where phone=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {

            preparedStatement.setString(1,client.getPhone());
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        res=resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return res;
    }


    public String getPibById(Clients client){
        String res="";
        String sql1 = "Select pib From Clients Where id_client=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {

            preparedStatement.setInt(1,client.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            res=resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return res;
    }

}
