package com.tavisca.citysearch.services;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.models.LocationSearchResponse;
import com.tavisca.citysearch.sources.LocationInfoSource;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LocationSearchServiceTest {

    private final String query = "Pune";
    private final String category = "Historic Site";

    private LocationInfoSource fourSquareSource = mock(LocationInfoSource.class);
    private LocationInfoSource googleMapsSource = mock(LocationInfoSource.class);

    private LocationInfo fourSquareLocationInfo = mock(LocationInfo.class);
    private LocationInfo googleMapsLocationInfo = mock(LocationInfo.class);


    @Test
    public void shouldSearchInAllLocationInfoSourcesIfCategoryIsNull() {
        LocationSearchRequest searchRequest = new LocationSearchRequest(query, null);

        when(fourSquareSource.getLocationInfo(searchRequest)).thenReturn(fourSquareLocationInfo);
        when(googleMapsSource.getLocationInfo(searchRequest)).thenReturn(googleMapsLocationInfo);

        LocationSearchService searchService = new LocationSearchService(asList(fourSquareSource, googleMapsSource));

        LocationSearchResponse searchResponse = searchService.search(searchRequest);

        verify(fourSquareSource).getLocationInfo(searchRequest);
        verify(googleMapsSource).getLocationInfo(searchRequest);

        assertEquals(asList(fourSquareLocationInfo, googleMapsLocationInfo), searchResponse.getData());
    }

    @Test
    public void shouldSearchInAllLocationInfoSources() {
        LocationSearchRequest searchRequest = new LocationSearchRequest(query, category);

        when(fourSquareSource.getLocationInfo(searchRequest)).thenReturn(fourSquareLocationInfo);
        when(googleMapsSource.getLocationInfo(searchRequest)).thenReturn(googleMapsLocationInfo);

        LocationSearchService searchService = new LocationSearchService(asList(fourSquareSource, googleMapsSource));

        LocationSearchResponse searchResponse = searchService.search(searchRequest);

        verify(fourSquareSource).getLocationInfo(searchRequest);
        verify(googleMapsSource).getLocationInfo(searchRequest);

        assertEquals(asList(fourSquareLocationInfo, googleMapsLocationInfo), searchResponse.getData());
    }
}