package com.tavisca.citysearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationSearchRequest {
    private String query;
    private String category;
}
