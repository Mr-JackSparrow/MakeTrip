package com.test.tripproject.services;

import com.test.tripproject.exceptions.CustomUserException;
import com.test.tripproject.model.dtos.UserDTO;
import com.test.tripproject.model.dtos.UserDetailsDTO;
import com.test.tripproject.model.entities.UserEntity;
import com.test.tripproject.repositories.UserDao;
import com.test.tripproject.repositories.impls.UserDaoImpl;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import static com.test.tripproject.services.utils.Converter.convertUserDtoToEntity;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    public void updateUser(UserDTO user, String existingEmailId){

        if(userDao.findUserByEmailId(user.getEmailId()) != null){
            throw new CustomUserException(String.format("USER WITH : (%s) ALREADY EXISTS IN DATABASE", existingEmailId));
        }else{
            int rowCount = userDao.update(convertUserDtoToEntity(user), existingEmailId);

            if(rowCount <= 0) {
                throw new CustomUserException(String.format("USER WITH : (%s) WAS NOT UPDATED", existingEmailId));
            }
        }
    }

    public void deleteUser(String emailId){

        int rowCount = userDao.delete(emailId);

        if(rowCount <= 0){
            throw new CustomUserException("USER NOT DELETED");
        }
    }

    public UserDTO findUserByEmailId(String email){

        UserEntity user = userDao.findUserByEmailId(email);

        if(user != null){
            return new UserDTO(){
                {
                    Point location = user.getLocation();
                    setFirstName(user.getFirstName());
                    setLastName(user.getLastName());
                    setMobileNo(user.getMobileNo());
                    setEmailId(user.getEmailId());
                    setAddress(user.getAddress());
                    setLongitude(location.getX());
                    setLatitude(location.getY());
                }
            };
        }else{
            throw new CustomUserException("SOMETHING WENT WRONG, CHECK IF DATA IS CORRECT");
        }
    }
}
