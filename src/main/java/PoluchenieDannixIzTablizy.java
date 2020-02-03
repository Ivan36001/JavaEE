import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PoluchenieDannixIzTablizy {
    public static void main(String[] args) {
        try{
            String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1234";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");
                while(resultSet.next()){
                    //Навигация по столбцам:
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    System.out.printf("%d. %s - %d \n", id, name, price);//см. форматирование
                    //Навигация по названию столбцов:
                   // int id = resultSet.getInt("Id");
                   // String name = resultSet.getString("ProductName");
                   // int price = resultSet.getInt("Price");


                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}

