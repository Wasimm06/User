package com.example.User.ServiceImplementation;

import com.example.User.DTO.CreateUserRequestDTO;
import com.example.User.DTO.CreateUserResponseDTO;
import com.example.User.DTO.ListUserResponseDTO;
import com.example.User.DTO.ShowUserDetailResponseDTO;
import com.example.User.DTO.UpdateUserRequestDTO;
import com.example.User.Service.UserService;

public class UserServiceImplementation implements UserService {

    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public ListUserResponseDTO listUsers(String filter, int startIndex, int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listUsers'");
    }

    @Override
    public CreateUserResponseDTO updateUser(String id, UpdateUserRequestDTO requestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public ShowUserDetailResponseDTO showUserDetails(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showUserDetails'");
    }

    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }
    
}
