package com.example.User.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class UpdateUserRequestDTO {

    private List<String> schemas;

    @JsonProperty("Operations")   // 🔥 IMPORTANT (SCIM support)
    private List<OperationDTO> operations;
}