package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.sources.LocationInfoSource;
import com.tavisca.citysearch.sources.foursquare.models.FourSquareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FoursquareLocationInfoSource implements LocationInfoSource {

    private final RestTemplate restTemplate;
    private final FoursquareResponseMapper responseMapper;
    private final FoursquareConfiguration fourSquareConfiguration;

    @Autowired
    public FoursquareLocationInfoSource(RestTemplate restTemplate, FoursquareResponseMapper responseMapper, FoursquareConfiguration foursquareConfiguration) {
        this.restTemplate = restTemplate;
        this.responseMapper = responseMapper;
        this.fourSquareConfiguration = foursquareConfiguration;
    }

    @Override
    public LocationInfo getLocationInfo(LocationSearchRequest request) {
        return responseMapper
                .mapFrom(restTemplate
                        .getForEntity(fourSquareConfiguration
                                .buildUrl(request.getQuery()), FourSquareResponse.class).getBody(), getName());
    }


    @Override
    public String getName() {
        return "Foursquare";
    }
}
