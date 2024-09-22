package com.test.tripproject.model.repositories.impls;

import com.test.tripproject.model.entities.UserRegistrationEntity;
import com.test.tripproject.model.repositories.UserDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserDaoImpl implements UserDao {


    private final JdbcTemplate jt;
    private String query;

    public UserDaoImpl(JdbcTemplate jt){
        this.jt = jt;
    }

    @Override
    public int insert(UserRegistrationEntity user){

        query = "INSERT INTO users(\"firstName\", \"lastName\", \"mobileNo\", \"emailId\", location) VALUES (?, ?, ?, ?, ?)";

        KeyHolder key = new GeneratedKeyHolder();

        try{
            return jt.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(query, new String[]{"userId"});

                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getMobileNo());
                ps.setString(4, user.getEmailId());
                ps.setString(5, user.getLocation());

                return ps;
            }, key);


        }catch(DataAccessException e){
            return 0;
        }
    }
}
