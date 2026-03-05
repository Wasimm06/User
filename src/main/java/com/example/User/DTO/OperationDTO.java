package com.example.User.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {

    private String op;
    private String path;
    private List<ValueDTO> value;
}