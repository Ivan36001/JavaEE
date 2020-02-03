import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.concurrent.Callable;

public class LibraryServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter pw = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            //Метод Class.forName() в качестве параметра принимает строку, которая представляет полный путь к классу драйвера
            // с учетом всех пакетов. В случае MySQL это путь "com.mysql.cj.jdbc.Driver". Таким образом, Метод Class.forName
            // загружает класс драйвера, который будет использоваться.
            //Далее вызывается метод getDeclaredConstructor(), который возвращает конструктор данного класса. И в конце
            // вызывается метод newInstance(), который создает с помощью конструктора объект данного класса. И после этого
            // мы сможем взаимодействовать с сервером MySQL.
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/java_ee_db?serverTimezone=Europe/Moscow&useSSL=false", "root", "1234");
           // Метод DriverManager.getConnection в качестве параметров принимает адрес источника данных, логин и пароль.
            // В качестве логина и пароля передаются логин и пароль от сервера MySQL. Адрес локальной базы данных MySQL
            // указывается в следующем формате: jdbc:mysql://localhost/название_базы данных
            //Параметры подключения указываются после вопросительного знака после названия базы данных. Параметр serverTimezone
            // казывает на название часового пояса сервера бд. В данном случае "Europe/Moscow", cписок всех допустимых
            // названий часовых поясов можно найти на странице https://gist.github.com/kinjal/9105369. И параметр
            // useSSL=false указывает, что SSL не будет применяться.
            Statement stmt = conn.createStatement();//Для взаимодействия с базой данных приложение отправляет серверу MySQL
            // команды на языке SQL. Чтобы выполнить команду, вначале необходимо создаеть объект Statement.
            //Для его создания у объекта Connection вызывается метод createStatement():
            ResultSet rs = stmt.executeQuery("SELECT title from books");//executeQuery: выполняет команду SELECT (sql-запрос).
            // Возвращает объект ResultSet, который содержит результаты запроса. Можно даже написать SELECT * from books а в методе getString()
            //указать нужное поле
            /*
            executeUpdate: выполняет такие команды, как INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE.
            качестве результата возвращает количество строк, затронутых операцией (например, количество добавленных,
            измененных или удаленных строк), или 0, если ни одна строка не затронута операцией или если команда не изменяет
            содержимое таблицы (например, команда создания новой таблицы)
             */
            /*
            execute(): выполняет любые команды и возвращает значение boolean: true - если команда возвращает набор строк (SELECT), иначе возвращается false.
             */
            //Типичное перемещение по набору строк:
            while (rs.next())//next() возвращает true пока есть элементы в ResultSet
                //В объекте ResultSet итератор устаналивается на позиции перед первой строкой. И чтобы переместиться к
                // первой строке (и ко всем последующим) необходимо вызвать метод next(). Пока в наборе ResultSet есть
                // доступные строки, метод next будет возвращать true. Типичное перемещение по набору строк:
                pw.println(rs.getString("title"));//проходимся по всем элементам и выводим на печать в качестве аргументов
            //название интерисующего нас поля
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

