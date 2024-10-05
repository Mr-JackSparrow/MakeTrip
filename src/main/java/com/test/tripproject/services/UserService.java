package com.test.tripproject.services;

import com.test.tripproject.exceptions.CustomException;
import com.test.tripproject.model.dtos.requestDTOs.RequestUpdateUserDTO;
import com.test.tripproject.model.dtos.responseDTOs.ResponseUserDTO;
import com.test.tripproject.model.dtos.responseDTOs.detailsDTOs.ResponseUserDetailsDTO;
import com.test.tripproject.model.entities.UserEntity;
import com.test.tripproject.repositories.UserDAO;
import com.test.tripproject.repositories.impls.UserDAOImpl;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import static com.test.tripproject.services.utils.Converter.convertUpdateUserDtoToUserEntity;

@Service
public class UserService {

    private UserDAO userDao;

    public UserService(UserDAOImpl userDao){
        this.userDao = userDao;
    }

    public String updateUser(RequestUpdateUserDTO user, String existingEmailId){

        if(userDao.findUserByEmailId(user.getEmailId()) != null){
            throw new CustomException(String.format("USER WITH : (%s) ALREADY EXISTS IN DATABASE", existingEmailId));
        }else{
            int rowCount = userDao.update(convertUpdateUserDtoToUserEntity(user), existingEmailId);

            if(rowCount <= 0) {
                throw new CustomException(String.format("USER WITH EMAIL ID : (%s) WAS NOT UPDATED", existingEmailId));
            }else{
                return String.format("USER WITH EMAIL ID : (%s) UPDATED SUCCESSFULLY", existingEmailId);
            }
        }
    }

    public String deleteUser(String emailId){

        int rowCount = userDao.delete(emailId);

        if(rowCount <= 0){
            throw new CustomException("USER NOT DELETED");
        }else{
            return String.format("USER WITH EMAIL ID : %s DELETED SUCCESSFULLY", emailId);
        }
    }

    public ResponseUserDTO findUserByEmailId(String email){

        UserEntity user = userDao.findUserByEmailId(email);

        if(user != null){
            return new ResponseUserDTO(){
                {
                    Point location = user.getLocation();
                    setUserId(user.getUserId());
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
            throw new CustomException("SOMETHING WENT WRONG, CHECK IF DATA IS CORRECT");
        }
    }

    public ResponseUserDetailsDTO findUserById(int userId){

        UserEntity user = userDao.findUserById(userId);

        if(user != null){
            return new ResponseUserDetailsDTO(){
                {
                    Point location = user.getLocation();
                    setUserId(user.getUserId());
                    setFirstName(user.getFirstName());
                    setLastName(user.getLastName());
                    setMobileNo(user.getMobileNo());
                    setEmailId(user.getEmailId());
                    setCreatedAt(user.getCreatedAt());
                    setUpdatedAt(user.getUpdatedAt());
                    setAddress(user.getAddress());
                    setLongitude(location.getX());
                    setLatitude(location.getY());
                    setPassword(user.getPassword());
                }
            };
        }else{
            throw new CustomException("SOMETHING WENT WRONG, CHECK IF DATA IS CORRECT");
        }
    }
}
