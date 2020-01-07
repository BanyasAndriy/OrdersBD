package service;

import dao.StuffDao;
import entity.Clients;
import entity.Stuff;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuffService extends Utils implements StuffDao {

    private Connection connection = getConnection();

    @Override
    public void add(Stuff stuff) {
        if (!stuffAlreadyExist(stuff)){
            String sql="Insert into Stuff (name,description,price) values (?,?,?)";
            try (PreparedStatement pr = connection.prepareStatement(sql)) {

                pr.setString(1,stuff.getName());
                pr.setString(2,stuff.getDescription());
                pr.setInt(3,stuff.getPrice());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }}else {
            System.out.println("Такий товар вже доданий!!!!");
        }
        stuff.setStuffId(getIdByName(stuff));
    }

    @Override
    public List<Stuff> getAll() {
        List<Stuff> list = new ArrayList<>();

        String sql = "Select * from Stuff";
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Stuff stuff = new Stuff();
                stuff.setStuffId(resultSet.getInt("id_stuff"));
                stuff.setName(resultSet.getString("name"));
                stuff.setDescription(resultSet.getString("description"));
                stuff.setPrice(resultSet.getInt("price"));

                list.add(stuff);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    @Override
    public void update(Stuff newStuff, Stuff oldStuff) {

        String sql = "Update Stuff Set name=? , description=? , price=? where name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,newStuff.getName());
            preparedStatement.setString(2,newStuff.getDescription());
            preparedStatement.setInt(3,newStuff.getPrice());
            preparedStatement.setString(4,oldStuff.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void delete(Stuff stuff) {


        String sql = "Delete From Stuff where name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,stuff.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean stuffAlreadyExist(Stuff stuff){

        boolean result = false;
        List<Stuff> stuffs = new ArrayList<>();

        stuffs=getAll();

        for (Stuff cl: stuffs) {
            if (cl.getName().equals(stuff.getName()))
                result = true;
        }

        return result;
    }

    public int getIdByName(Stuff stuff) {
        int res = 0;
        String sql1 = "Select id_stuff From Stuff Where name =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {

            preparedStatement.setString(1, stuff.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            res = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return res;
    }
    public String getNameById(Stuff stuff){
        String res="";
        String sql1 = "Select name From Stuff Where id_stuff=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {

            preparedStatement.setInt(1,stuff.getStuffId());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            res=resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
