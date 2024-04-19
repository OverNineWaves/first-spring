package org.rinzler.spring.DAO;

import org.rinzler.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    //    private static final String URL = "jdbc:mysql://localhost:3306/springLessons";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "12345678Aa!";
//
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private List<Person> personList = new ArrayList<>();
//
//    {
//        personList.add(new Person(0, "name1",20, "email1@email.com"));
//        personList.add(new Person(1, "name2",21, "email2@email.com"));
//        personList.add(new Person(2, "name3",22, "email3@email.com"));
//        personList.add(new Person(3, "name4",23, "email4@email.com"));
//        personList.add(new Person(4, "name5",24, "email5@email.com"));
//    }

    public List<Person> index() {
        List<Person> personList = new ArrayList<>();
        return jdbcTemplate.query("SELECT * FROM Person", new personMapper());
//        try {
//            Statement statement = connection.createStatement();
//            String indexQ= "SELECT * FROM springLessons.Person";
//            ResultSet resultSet = statement.executeQuery(indexQ);
//
//            while (resultSet.next()){
//                Person person = new Person();
//
//               person.setId(resultSet.getInt("id"));
//               person.setName(resultSet.getString("name"));
//               person.setAge(resultSet.getInt("age"));
//               person.setEmail(resultSet.getString("email"));
//
//               personList.add(person);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String sql = "SELECT * FROM springLessons.Person";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                Person person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//
//                personList.add(person);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return personList;
    }

//    public Person show1(int id){
//        Person a;
//        for (int i = personList.size()-1; i >= 0; i--){
//            if (personList.get(i).getId() == id)
//            {
//               //System.out.println("равны");
//               a = personList.get(id);
//                return a;
//            }
//        }
//       return null;
//    }


    public Person show(String email){
        String sql = "select * from Person where email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, /*new personMapper()*/ new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
//        Person person = null;
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM springLessons.Person WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            person = new Person();
//            resultSet.next();
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setAge(resultSet.getInt("age"));
//            person.setEmail(resultSet.getString("email"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return person;


//        for(Person ids : personList){
//            if (ids.getId() == id){
//                return ids;
//            }
//        }
//        return null;
    }
//    public Person show2(int id){
//        return personList.get(1); //test
//        return personList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//        return null;
//    }

    public void create(Person person) {
        String sql = "INSERT INTO Person(name, age, email, address) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getEmail(),person.getAddress());
//        try {
//            Statement statement = connection.createStatement();
//            String createQ = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')";
//            statement.executeUpdate(createQ);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        person.setId(personList.size());
//        personList.add(person);

//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void edit(Person person, int id) {
        String sql = "UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?";
        jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getEmail(), person.getAddress(), id);
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE springLessons.Person SET name=?, age=?, email=? WHERE id=?");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        Person updatePerson = show(id);
//        updatePerson.setName(person.getName());
//        updatePerson.setAge(person.getAge());
//        updatePerson.setEmail(person.getEmail());
    }

    public void delete(int id) {
        String sql = "DELETE FROM Person WHERE id = ?";

        jdbcTemplate.update(sql, id);
//        for (int i = 0; personList.size() > i; i++){
//            if (i == id)
//                personList.remove(personList.get(i));
//        }

//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }
}