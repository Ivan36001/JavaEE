import java.sql.*;

public class SozdanieTablizy{

    public static void main(String[] args) {
        try{
            String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1234";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            // команда создания таблицы
            String sqlCommand = "CREATE TABLE products (Id INT PRIMARY KEY AUTO_INCREMENT, ProductName VARCHAR(20), Price INT)"; // команда создания таблицы

            try (Connection conn = DriverManager.getConnection(url, username, password)){//получение объекта Connection

                Statement statement = conn.createStatement();//получение объекта Statement

                statement.executeUpdate(sqlCommand); // создание таблицы
                 /*
            executeUpdate: выполняет такие команды, как INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE.
            в качестве результата возвращает количество строк, затронутых операцией (например, количество добавленных,
            измененных или удаленных строк), или 0, если ни одна строка не затронута операцией или если команда не изменяет
            содержимое таблицы (например, команда создания новой таблицы)
             */
                int rows = statement.executeUpdate("INSERT Products(ProductName, Price) VALUES ('iPhone X', 76000)," +
                        "('Galaxy S9', 45000), ('Nokia 9', 36000),"+"('Дичь S9', 45000), ('Дичь 9', 36000)");//добавление данных

                System.out.println("Table has been created!");
                System.out.printf("Added %d rows", rows);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}
//добавление драйвера mysql (jar): file->Project Structure->modules->Dependencies-> + ->JARs or directories->ищем драйвер->OK