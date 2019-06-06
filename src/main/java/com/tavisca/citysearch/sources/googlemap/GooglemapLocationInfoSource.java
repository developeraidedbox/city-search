package com.tavisca.citysearch.sources.googlemap;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.sources.LocationInfoSource;
import org.springframework.stereotype.Component;

@Component
public class GooglemapLocationInfoSource implements LocationInfoSource {

    @Override
    public String getName() {
        return "Google Map";
    }

    @Override
    public LocationInfo getLocationInfo(LocationSearchRequest request) {
        return new LocationInfo();
    }
}
