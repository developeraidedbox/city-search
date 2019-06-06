package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.sources.LocationInfoSource;
import org.springframework.stereotype.Component;

@Component
public class FoursquareLocationInfoSource implements LocationInfoSource {

    @Override
    public LocationInfo getLocationInfo(LocationSearchRequest request) {
        return new LocationInfo();
    }


    @Override
    public String getName() {
        return "Foursquare";
    }
}
