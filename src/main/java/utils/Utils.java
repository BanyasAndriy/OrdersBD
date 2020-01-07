package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {

    final static String url = "jdbc:mysql://localhost:3306/OrdersDB?useLegacyDatetimeCode=false&serverTimezone=Europe/Kiev" ;
    final static String login="root";
    final static String parol="280200";


     public Connection getConnection(){
         Connection connection = null;
         try {
              connection = DriverManager.getConnection(url,login,parol);
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("Зєднання втраченно!!!");

         }
         return connection;
     }

}
