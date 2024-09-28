package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ResultSetDemo {

    public static String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/studentdb";
    public static String PASSWORD="Rajshah1103";
    public static String USERNAME="root";
    public static void main (String [] args) {
        try {
            // load driver
            Class.forName(LOAD_DRIVER);

            // make connection
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            // create statement
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE
            );
            // type scroll insensitive means the db data is scrollable but not reflected in our result set when someone makes changes directly in the db
            // concur updatable lets you update the db through the code and this changes will be reflected in the db
            // now when we use both scroll insensitive and concur updatable we say that we want to make changes to the db through our code but we dont want that chnages to be reflected in out resultSet

            // create query
            String query = "select * from students";
            ResultSet resultSet = statement.executeQuery(query);


            resultSet.first();
            System.out.println("Before insert operation first row");
            System.out.println("fetching last row data");
            System.out.println("ID: "+resultSet.getInt("id"));
            System.out.println("Name: "+resultSet.getString("name"));

            resultSet.last();
            System.out.println("Before insert operation last row");
            System.out.println("fetching last row data");
            System.out.println("ID: "+resultSet.getInt("id"));
            System.out.println("Name: "+resultSet.getString("name"));

            resultSet.updateString("name", "Akash");
            resultSet.updateRow();

            resultSet.moveToInsertRow();
            resultSet.updateInt("id", 5);
            resultSet.updateString("name", "sooraj");
            resultSet.updateString("course", "system design");
            resultSet.insertRow();

            resultSet.first();
            System.out.println("After insert operation first row");
            System.out.println("fetching last row data");
            System.out.println("ID: "+resultSet.getInt("id"));
            System.out.println("Name: "+resultSet.getString("name"));

            resultSet.last();
            System.out.println("After insert operation last row");
            System.out.println("fetching last row data");
            System.out.println("ID: "+resultSet.getInt("id"));
            System.out.println("Name: "+resultSet.getString("name"));

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

