package com.example.User.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListUserResponseDTO {

    // Required - must be ListResponse schema
    private List<String> schemas;

    // Pagination fields
    private int startIndex;
    private int itemsPerPage;

    // Required - total count
    private int totalResults;

    // Must be exactly "Resources" as per SCIM spec
    @JsonProperty("Resources")
    private List<ResourceResponseDTO> resources;
}