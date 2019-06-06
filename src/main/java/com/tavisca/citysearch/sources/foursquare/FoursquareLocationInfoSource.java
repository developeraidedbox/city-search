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
    private final FoursquareResponseFilter foursquareResponseFilter;

    @Autowired
    public FoursquareLocationInfoSource(RestTemplate restTemplate, FoursquareResponseMapper responseMapper, FoursquareConfiguration foursquareConfiguration, FoursquareResponseFilter foursquareResponseFilter) {
        this.restTemplate = restTemplate;
        this.responseMapper = responseMapper;
        this.fourSquareConfiguration = foursquareConfiguration;
        this.foursquareResponseFilter = foursquareResponseFilter;
    }

    @Override
    public LocationInfo getLocationInfo(LocationSearchRequest request) {

        String foursquareUrl = fourSquareConfiguration.buildUrl(request.getQuery());

        LocationInfo locationInfo = responseMapper
                .mapFrom(restTemplate.getForEntity(foursquareUrl, FourSquareResponse.class).getBody(), getName());

        return foursquareResponseFilter.filter(request, locationInfo);
    }

    @Override
    public String getName() {
        return "Foursquare";
    }
}
