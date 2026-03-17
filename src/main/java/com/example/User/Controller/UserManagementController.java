package com.example.User.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.User.DTO.CreateUserRequestDTO;
import com.example.User.DTO.CreateUserResponseDTO;
import com.example.User.DTO.ShowUserDetailResponseDTO;
import com.example.User.Service.UserManagementService;

@RestController
@RequestMapping("/v1/users")
public class UserManagementController {

    private final UserManagementService userService;

    @Autowired
    public UserManagementController(UserManagementService userService) {
        this.userService = userService;
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(
            @RequestBody CreateUserRequestDTO requestDTO) {

        CreateUserResponseDTO response = userService.createUser(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // SHOW USER DETAILS
    @GetMapping("/{id}")
    public ResponseEntity<ShowUserDetailResponseDTO> showUserDetails(@PathVariable UUID id) {
        ShowUserDetailResponseDTO response = userService.showUserDetails(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}