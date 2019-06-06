package com.tavisca.citysearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LocationSearchResponse {
    private final String query;
    private final List<LocationInfo> data;
}
