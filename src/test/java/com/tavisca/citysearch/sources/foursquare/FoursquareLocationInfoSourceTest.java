package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.sources.foursquare.models.FourSquareResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class FoursquareLocationInfoSourceTest {

    private final String query = "Pune";
    private final String category = "Historic Site";
    private final String foursquareApiUrl = "someurl";
    @Mock
    LocationInfo locationInfo;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private FoursquareResponseMapper mapper;
    @Mock
    private FoursquareConfiguration foursquareConfiguration;
    @Mock
    private FourSquareResponse fourSquareResponse;

    @Test
    public void shouldCallFoursquareApiToGetLocationInfo() throws Exception {
        LocationSearchRequest searchRequest = new LocationSearchRequest(query, category);

        FoursquareLocationInfoSource foursquareLocationInfoSource = new FoursquareLocationInfoSource(restTemplate, mapper, foursquareConfiguration);

        when(foursquareConfiguration.buildUrl(query)).thenReturn(foursquareApiUrl);
        when(mapper.mapFrom(fourSquareResponse, foursquareLocationInfoSource.getName())).thenReturn(locationInfo);
        when(restTemplate.getForEntity(foursquareApiUrl, FourSquareResponse.class)).thenReturn(new ResponseEntity<>(fourSquareResponse, OK));

        LocationInfo foursquareLocationInfo = foursquareLocationInfoSource.getLocationInfo(searchRequest);

        verify(foursquareConfiguration).buildUrl(query);
        verify(restTemplate).getForEntity(foursquareApiUrl, FourSquareResponse.class);
        verify(mapper).mapFrom(fourSquareResponse, foursquareLocationInfoSource.getName());

        assertEquals(locationInfo, foursquareLocationInfo);
    }
}