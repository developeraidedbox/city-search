package com.tavisca.citysearch.resources;

import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.models.LocationSearchResponse;
import com.tavisca.citysearch.services.LocationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationSearchController {

    private final LocationSearchService locationSearchService;

    @Autowired
    public LocationSearchController(LocationSearchService locationSearchService) {
        this.locationSearchService = locationSearchService;
    }

    @GetMapping("/search")
    public LocationSearchResponse searchLocations(LocationSearchRequest searchRequest) {
        // Exceptions are handled by com.tavisca.citysearch.resources.handlers.BasicExceptionHandler
        return locationSearchService.search(searchRequest);
    }
}
