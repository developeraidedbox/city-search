package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.sources.ResponseMapper;
import com.tavisca.citysearch.sources.foursquare.models.FourSquareResponse;
import org.springframework.stereotype.Component;

@Component
public class FoursquareResponseMapper implements ResponseMapper<FourSquareResponse> {

    @Override
    public LocationInfo mapFrom(FourSquareResponse response, String source) {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setSource(source);
        return locationInfo;
    }
}
