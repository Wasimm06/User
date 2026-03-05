package com.example.User.DTO;

import lombok.Data;
import java.util.List;

@Data
public class UpdateUserRequestDTO {
     private List<String> schemas;
    private List<OperationDTO> Operations;
    
}

