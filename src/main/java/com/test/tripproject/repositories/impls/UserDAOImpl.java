package com.test.tripproject.repositories.impls;

import com.test.tripproject.model.entities.UserEntity;
import com.test.tripproject.repositories.UserDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.Point;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class UserDAOImpl implements UserDAO {


    private final JdbcTemplate jt;
    private String query;

    public UserDAOImpl(JdbcTemplate jt){
        this.jt = jt;
    }

    @Override
    public int insert(UserEntity user){

        query = "INSERT INTO users(\"firstName\", \"lastName\", \"mobileNo\", \"emailId\", \"createdAt\", \"updatedAt\", address, location, password) VALUES (?, ?, ?, ?, ?, ?, ?, ST_SetSRID(ST_Point(?, ?), 4326), ?)";

        KeyHolder key = new GeneratedKeyHolder();

        try{
            jt.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(query, new String[]{"userId"});

                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getMobileNo());
                ps.setString(4, user.getEmailId());
                ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(7, user.getAddress());
                ps.setDouble(8, user.getLocation().getX());
                ps.setDouble(9, user.getLocation().getY());
                ps.setString(10, user.getPassword());

                return ps;
            }, key);

            if(key.getKey() != null){
                return (int) key.getKey();
            }else{
                return 0;
            }

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

        query = "SELECT *, ST_X(location) as longitude, ST_Y(location) as latitude FROM users WHERE \"emailId\" = ?";

        try{
            return jt.queryForObject(query, (res, rowNum) -> {
                UserEntity user = new UserEntity();

                user.setUserId(res.getLong("userId"));
                user.setFirstName(res.getString("firstName"));
                user.setLastName(res.getString("lastName"));
                user.setMobileNo(res.getString("mobileNo"));
                user.setEmailId(res.getString("emailId"));
                user.setAddress(res.getString("address"));
                user.setLocation(new Point(res.getDouble("longitude"), res.getDouble("latitude")));

                return user;

        }, emailId);

        }catch(DataAccessException e){
            return null;
        }
    }

    @Override
    public UserEntity findUserById(int userId) {

        query = "SELECT *, ST_X(location) as longitude, ST_Y(location) as latitude FROM users WHERE \"userId\" = ?";

        try{
            return jt.queryForObject(query, (res, rowNum) ->
            {
                UserEntity user = new UserEntity();

                user.setUserId(res.getLong("userId"));
                user.setFirstName(res.getString("firstName"));
                user.setLastName(res.getString("lastName"));
                user.setMobileNo(res.getString("mobileNo"));
                user.setEmailId(res.getString("emailId"));
                user.setCreatedAt(res.getTimestamp("createdAt"));
                user.setUpdatedAt(res.getTimestamp("updatedAt"));
                user.setAddress(res.getString("address"));
                user.setLocation(new Point(res.getDouble("longitude"), res.getDouble("latitude")));
                user.setPassword(res.getString("password"));

                return user;
            }, userId);
        }catch(DataAccessException e){
            return null;
        }
    }
}