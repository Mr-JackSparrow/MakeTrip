package com.test.tripproject.controllers;

import com.test.tripproject.model.dtos.UpdateRequestDTO;
import com.test.tripproject.model.dtos.UserDTO;
import com.test.tripproject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/user/{emailId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findUserByEmail(@PathVariable String emailId){

        try{
            UserDTO user = userService.findUserByEmailId(emailId);

            return ResponseEntity
                    .ok()
                    .body(user);

        }catch(Exception e){

            return ResponseEntity
                    .ok()
                    .body(
                            e.getMessage()
                    );
        }
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody UpdateRequestDTO request){

        try{

            userService.updateUser(request.getUserDTO(), request.getEmail());

            return ResponseEntity
                    .ok()
                    .body(
                            "USER UPDATED SUCCESSFULLY"
                    );
        }catch(Exception e){

            return ResponseEntity
                    .ok()
                    .body(
                            e.getMessage()
                    );
        }
    }

    @RequestMapping(value = "/user/delete/{emailId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable String emailId){

        try{
            userService.deleteUser(emailId);

            return ResponseEntity
                    .ok()
                    .body(
                            String.format("USER WITH : (%s) DELETED SUCCESSFULLY", emailId)
                    );
        }catch(Exception e){
            return ResponseEntity
                    .ok()
                    .body(
                            e.getMessage()
                    );
        }
    }
}
