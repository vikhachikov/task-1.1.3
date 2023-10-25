package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connect = null;
    public Util() {

    }
    private static Properties getAplication(){
        Properties properties = new Properties();
        try(InputStream in = Util.class.getClassLoader().getResourceAsStream("application.properties")){
            properties.load(in);

        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static Connection getconnect() {
        Properties properties = getAplication();
        try {
            connect = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
            if (connect != null) {
                System.out.println("Connect");

            } else {
                System.out.println("Disconnect");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return connect;
    }
}
