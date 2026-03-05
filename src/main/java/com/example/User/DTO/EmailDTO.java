package com.example.User.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EmailDTO {

    private String type;
    private Boolean primary;
    private String value;

    // getters & setters
}