package com.tavisca.citysearch.models;

import lombok.Data;

import java.util.List;

@Data
public class FilteredSearchResponse extends LocationSearchResponse {

    private final String category;

    public FilteredSearchResponse(String query, String category, List<LocationInfo> data) {
        super(query, data);
        this.category = category;
    }
}
