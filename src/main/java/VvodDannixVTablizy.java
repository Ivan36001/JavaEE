import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class VvodDannixVTablizy {
    public static void main(String[] args) {

        try{
            String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1234";
            Scanner scanner = new Scanner(System.in);

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            System.out.print("Введите поле name: ");
            String name = scanner.nextLine();

            System.out.print("Введите поле price: ");
            int price = scanner.nextInt();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO Products (ProductName, Price) Values (?, ?)";// Для создания объекта PreparedStatement
                // применяется метод prepareStatement() класса Connection. В этот метод передается выражение
                // sql INSERT INTO Products (ProductName, Price) Values (?, ?). Это выражение может содержать знаки вопроса ?
                // - знаки подстановки, вместо которых будут вставляться реальные значения.
                PreparedStatement preparedStatement = conn.prepareStatement(sql);//создание объекта preparedStatement
                preparedStatement.setString(1, name);// Все методы, которые поставляют значения вместо знаков подстановки,
                // в качестве первого параметра принимают порядковый номер знака подстановки-"?" (нумерация начинается с 1),
                // а в качестве второго параметра - собственно значение, которое вставляется вместо знака подстановки.
                preparedStatement.setInt(2, price);
                /*
                Для выполнения запроса PreparedStatement имеет три метода:
               boolean execute(): выполняет любую SQL-команду
               ResultSet executeQuery(): выполняет команду SELECT, которая возвращает данные в виде ResultSet
               executeUpdate(): выполняет такие SQL-команды, как INSERT, UPDATE, DELETE, CREATE и возвращает количество измененных строк
                 */
                int rows = preparedStatement.executeUpdate();//выполнение запроса

                System.out.printf("%d rows added", rows);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

/*
Подобным образом мы можем выполнять и другие выражения. Например, получим товары, у которых цена меньше 50000:
int maxPrice = 50000;
PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Products WHERE Price < ?");
preparedStatement.setInt(1, maxPrice);
ResultSet resultSet = preparedStatement.executeQuery();
while(resultSet.next()){

    int id = resultSet.getInt("Id");
    String name = resultSet.getString("ProductName");
    int price = resultSet.getInt("Price");

    System.out.printf("%d. %s - %d \n", id, name, price);
}
 */




}
