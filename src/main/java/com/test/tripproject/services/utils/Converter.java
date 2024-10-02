package com.test.tripproject.services.utils;

import com.test.tripproject.model.dtos.UserDTO;
import com.test.tripproject.model.dtos.UserDetailsDTO;
import com.test.tripproject.model.entities.UserEntity;
import org.springframework.data.geo.Point;

public class Converter {

    public static UserEntity convertUserDetailsDtoToEntity(UserDetailsDTO user){
        return new UserEntity(){
            {
                Point location = new Point(user.getLongitude(), user.getLatitude());
                setFirstName(user.getFirstName());
                setLastName(user.getLastName());
                setMobileNo(user.getMobileNo());
                setEmailId(user.getEmailId());
                setAddress(user.getAddress());
                setLocation(location);
                setPassword(user.getPassword());

            }
        };
    }

    public static UserEntity convertUserDtoToEntity(UserDTO user){
        return new UserEntity(){
            {
                Point location = new Point(user.getLongitude(), user.getLatitude());
                setFirstName(user.getFirstName());
                setLastName(user.getLastName());
                setMobileNo(user.getMobileNo());
                setEmailId(user.getEmailId());
                setAddress(user.getAddress());
                setLocation(location);

            }
        };
    }
}
