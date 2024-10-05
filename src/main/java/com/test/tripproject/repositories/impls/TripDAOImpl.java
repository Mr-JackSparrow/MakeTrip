package com.test.tripproject.repositories.impls;

import com.test.tripproject.exceptions.CustomException;
import com.test.tripproject.model.entities.TripEntity;
import com.test.tripproject.repositories.TripDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.Point;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TripDAOImpl implements TripDAO {


    private String query;
    private final JdbcTemplate jt;

    public TripDAOImpl(JdbcTemplate jt){
        this.jt = jt;
    }

    @Override
    public int create(TripEntity trip) {
        query = "INSERT INTO trips (\"tripMakerId\", \"tripDestinationDescription\", \"startDate\", \"endDate\", \"maxParticipants\", \"createdAt\", \"updatedAt\", location) VALUES(?, ?, ?, ?, ?, ?, ?, ST_SetSRID(ST_Point(?, ?), 4326))";

        KeyHolder key = new GeneratedKeyHolder();
        try{
            jt.update(connection ->
            {
                PreparedStatement ps = connection.prepareStatement(query, new String[]{"tripId"});

                ps.setLong(1, trip.getTripMakerId());
                ps.setString(2, trip.getTripDestinationDescription());
                ps.setDate(3, Date.valueOf(trip.getStartDate().toLocalDate()));
                ps.setDate(4, Date.valueOf(trip.getEndDate().toLocalDate()));
                ps.setLong(5, trip.getMaxParticipants());
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                ps.setDouble(8, trip.getLocation().getX());
                ps.setDouble(9, trip.getLocation().getY());
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
    public TripEntity readById(Long tripId) {

        query = "SELECT *, ST_X(location::geometry) AS longitude, ST_Y(location::geometry) as latitude FROM trips where \"tripId\" = ?";

        try{
             return jt.queryForObject(query, (res, rowNum) -> {

                 TripEntity trip = new TripEntity();
                 trip.setTripId(res.getLong("tripId"));
                 trip.setTripMakerId(res.getLong("tripMakerId"));
                 trip.setTripDestinationDescription(res.getString("tripDestinationDescription"));
                 trip.setStartDate(res.getDate("startDate"));
                 trip.setEndDate(res.getDate("endDate"));
                 trip.setMaxParticipants(res.getLong("maxParticipants"));
                 trip.setCreatedAt(res.getTimestamp("createdAt").toLocalDateTime());
                 trip.setUpdatedAt(res.getTimestamp("updatedAt").toLocalDateTime());
                 trip.setLocation(new Point(res.getDouble("longitude"), res.getDouble("latitude")));



                 return trip;
            },tripId);
        }catch(DataAccessException e){
            return null;
        }
    }

    @Override
    public List<TripEntity> readAll() {

        query = "SELECT *, ST_X(location::geometry) AS longitude, ST_Y(location::geometry) AS latitude FROM trips";

        try{
            return jt.query(query, (res, rowNum) -> {

                TripEntity trip = new TripEntity();

                trip.setTripId(res.getLong("tripId"));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+res.getLong("tripId"));
                trip.setTripMakerId(res.getLong("tripMakerId"));
                trip.setTripDestinationDescription(res.getString("tripDestinationDescription"));
                trip.setStartDate(res.getDate("startDate"));
                trip.setEndDate(res.getDate("endDate"));
                trip.setMaxParticipants(res.getLong("maxParticipants"));
                trip.setCreatedAt(res.getTimestamp("createdAt").toLocalDateTime());
                trip.setUpdatedAt(res.getTimestamp("updatedAt").toLocalDateTime());
                trip.setLocation(new Point(res.getDouble("longitude"), res.getDouble("latitude")));


                return trip;
            });
        }catch(DataAccessException e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }
}
