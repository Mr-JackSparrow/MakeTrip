package com.test.tripproject.repositories.impls;

import com.test.tripproject.exceptions.UserNotFoundException;
import com.test.tripproject.model.dtos.LoginDTO;
import com.test.tripproject.model.entities.UserEntity;
import com.test.tripproject.repositories.UserDao;
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
    public int insert(UserEntity user){

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


    @Override
    public LoginDTO findCredentialsByEmailId(String emailId){

        System.out.println("Before finding");
        query = "SELECT \"lastName\" FROM users WHERE \"emailId\" = ?";

        try{
            UserEntity user = jt.queryForObject(query, (res, rowNum) -> new UserEntity(){
                {
                    setLastName(res.getString("lastName"));
                }
            }, emailId);


            System.out.println("Before finding");

            return new LoginDTO(){{
               setUserName(emailId);
               setPassword(user.getLastName());
            }};
        }catch(DataAccessException e){
            System.out.println("finding exception");
            throw new UserNotFoundException();
        }
    }
}
