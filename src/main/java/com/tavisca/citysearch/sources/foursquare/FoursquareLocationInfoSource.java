package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.sources.LocationInfoSource;
import com.tavisca.citysearch.sources.foursquare.models.FourSquareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

        String foursquareUrl = fourSquareConfiguration.buildUrl(request.getQuery());
        ResponseEntity<FourSquareResponse> responseEntity = restTemplate.getForEntity(foursquareUrl, FourSquareResponse.class);

        return responseMapper.mapFrom(responseEntity.getBody(), getName());
    }


    @Override
    public String getName() {
        return "Foursquare";
    }
}
