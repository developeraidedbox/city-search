package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.models.Nearby;
import com.tavisca.citysearch.sources.ResponseFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FoursquareResponseFilterTest {

    private String query = "Pune";
    private String category = "Historic Site";

    private static final List<Nearby> nearby = new ArrayList<>();

    static {
        nearby.add(new Nearby("Shaniwar Wada", "Shaniwar Peth (Bajirao Road)", "Historic Site"));
        nearby.add(new Nearby("Cafe Peshwai", "Pune 411030", "Café"));
    }

    @Test
    public void shouldFilterResponseWithCategory()
    {

        LocationSearchRequest request = new LocationSearchRequest(query, category);

        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setName(query);
        locationInfo.setDisplayName(query);
        locationInfo.setNearby(nearby);
        locationInfo.setCountryCode("IN");

        LocationInfo result = new FoursquareResponseFilter().filter(request, locationInfo);

        assertEquals(query, result.getName());
        assertEquals(query, result.getDisplayName());
        assertEquals(1, result.getNearby().size());
        assertEquals(nearby.get(0).getKind(), result.getNearby().get(0).getKind());
    }

    @Test
    public void shouldFilterResponseWithOutCategory()
    {

        LocationSearchRequest request = new LocationSearchRequest(query, null);

        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setName(query);
        locationInfo.setDisplayName(query);
        locationInfo.setNearby(nearby);
        locationInfo.setCountryCode("IN");

        LocationInfo result = new FoursquareResponseFilter().filter(request, locationInfo);

        assertEquals(query, result.getName());
        assertEquals(query, result.getDisplayName());
        assertEquals(2, result.getNearby().size());
    }
}