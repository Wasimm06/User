package com.example.User.Controller;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.User.DTO.CreateUserRequestDTO;
import com.example.User.DTO.CreateUserResponseDTO;
import com.example.User.DTO.ListUserResponseDTO;
import com.example.User.DTO.ShowUserDetailResponseDTO;
import com.example.User.DTO.UpdateUserRequestDTO;
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

        // LIST USERS
    @GetMapping
    public ListUserResponseDTO listUsers(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "1") int startIndex,
            @RequestParam(defaultValue = "10") int count) {

        return userService.listUsers(filter, startIndex, count);

    }

    // UPDATE USER
  @PatchMapping("/{id}")
    public ResponseEntity<CreateUserResponseDTO> updateUser(
        @PathVariable UUID id,
        @RequestBody UpdateUserRequestDTO requestDTO) {

    return new ResponseEntity<>(userService.updateUser(id, requestDTO), HttpStatus.OK);
}

// DELETE USER
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
}

}