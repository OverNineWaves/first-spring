package org.rinzler.spring.DAO;

import org.rinzler.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BatchDAO {

    private final JdbcTemplate jdbcTemplate;

    public BatchDAO( JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void multi(){
        List<Person> list = createPeople();
        Long start = System.currentTimeMillis();
        String sql = "INSERT INTO Person(name, age, email) VALUES (?,?,?)";

        for (Person person : list) {
            jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getEmail());
        }

        Long after = System.currentTimeMillis();

        System.out.println(after - start);
    }
    public void batch(){
        List<Person> list = createPeople();
        Long start = System.currentTimeMillis();
        String sql = "INSERT INTO Person(name, age, email) VALUES (?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    //ps.setInt(1, list.get(i).getId());
                    ps.setString(1, list.get(i).getName());
                    ps.setInt(2, list.get(i).getAge());
                    ps.setString(3, list.get(i).getEmail());
                }

                @Override
                public int getBatchSize() {
                    return list.size();
                }
            });

        Long after = System.currentTimeMillis();

        System.out.println("batch time" + (after - start));
    }

    private List<Person> createPeople(){
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            personList.add(new Person(i,"name" + i,20+i,i + "email@email.com"));
        }
        return personList;
    }
}
