package com.tavisca.citysearch.resources;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.models.LocationSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class LocationSearchController {

    @GetMapping("/search")
    public LocationSearchResponse searchLocations(LocationSearchRequest searchRequest) {
        LocationSearchResponse locationSearchResponse = new LocationSearchResponse(searchRequest.getQuery(), searchRequest.getCategory(), Arrays.asList(new LocationInfo()));
        return locationSearchResponse;
    }
}
