package com.example.User.Service;

import java.util.UUID;

import com.example.User.DTO.CreateUserRequestDTO;
import com.example.User.DTO.CreateUserResponseDTO;
import com.example.User.DTO.UpdateUserRequestDTO;
import com.example.User.DTO.ListUserResponseDTO;
import com.example.User.DTO.ShowUserDetailResponseDTO;

public interface UserManagementService {

    /**
     * Create a new user
     */
    CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO);
  
   
   // ListUserResponseDTO listUsers(String filter, int startIndex, int count);

    // CreateUserResponseDTO updateUser(String id, UpdateUserRequestDTO requestDTO);

   
    ShowUserDetailResponseDTO showUserDetails(UUID id);

    // void deleteUser(String id);

} 

