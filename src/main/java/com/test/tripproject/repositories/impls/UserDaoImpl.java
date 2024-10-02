package com.test.tripproject.repositories.impls;

import com.test.tripproject.exceptions.CustomUserException;
import com.test.tripproject.model.entities.UserEntity;
import com.test.tripproject.repositories.UserDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.Point;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@Repository
public class UserDaoImpl implements UserDao {


    private final JdbcTemplate jt;
    private String query;

    public UserDaoImpl(JdbcTemplate jt){
        this.jt = jt;
    }

    @Override
    public int insert(UserEntity user){

        query = "INSERT INTO users(\"firstName\", \"lastName\", \"mobileNo\", \"emailId\", \"createdAt\", \"updatedAt\", address, location, password) VALUES (?, ?, ?, ?, ?, ?, ?, ST_SetSRID(ST_Point(?, ?), 4326), ?)";

        KeyHolder key = new GeneratedKeyHolder();

        try{
            return jt.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(query, new String[]{"userId"});

                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getMobileNo());
                ps.setString(4, user.getEmailId());
                ps.setObject(5, LocalDateTime.now());
                ps.setObject(6, LocalDateTime.now());
                ps.setObject(7, user.getAddress());
                ps.setDouble(8, user.getLocation().getX());
                ps.setDouble(9, user.getLocation().getY());
                ps.setString(10, user.getPassword());

                return ps;
            }, key);


        }catch(DataAccessException e){
            return 0;
        }
    }


    @Override
    public int update(UserEntity user, String existingEmailId){

        query = "UPDATE users SET \"firstName\" = ?, \"lastName\" = ?, \"mobileNo\" = ?, \"emailId\" = ?, \"updatedAt\" = ?, address = ?, location = ST_SetSRID(ST_Point(?, ?), 4326) WHERE \"emailId\" = ?";

        try{
            return jt.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(query);

                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getMobileNo());
                ps.setString(4, user.getEmailId());
                ps.setObject(5, LocalDateTime.now());
                ps.setString(6, user.getAddress());
                ps.setObject(7, user.getLocation().getX());
                ps.setObject(8, user.getLocation().getY());
                ps.setString(9, existingEmailId);
                return ps;
            });
        }catch(DataAccessException e){

            return 0;
        }
    }

    @Override
    public int delete(String emailId){

        query = "DELETE FROM users WHERE \"emailId\" = ?";

        try{
            return jt.update(query, emailId);
        }catch(DataAccessException e){
            return 0;
        }
    }

    @Override
    public UserEntity findUserByEmailId(String emailId){

        query = "SELECT \"firstName\", \"lastName\", \"mobileNo\", \"emailId\", address, ST_X(location) as longitude, ST_Y(location) as latitude, password FROM users WHERE \"emailId\" = ?";

        try{
            UserEntity user = jt.queryForObject(query, (res, rowNum) -> new UserEntity(){
                {
                    setFirstName(res.getString("firstName"));
                    setLastName(res.getString("lastName"));
                    setMobileNo(res.getString("mobileNo"));
                    setEmailId(res.getString("emailId"));
                    setAddress(res.getString("address"));
                    setLocation(new Point(res.getDouble("longitude"), res.getDouble("latitude")));
                }
            }, emailId);

            return user;

        }catch(DataAccessException e){
            return null;
        }
    }
}