package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.Nearby;
import com.tavisca.citysearch.sources.foursquare.models.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class FoursquareResponseMapperTest {

    private FoursquareResponseMapper foursquareResponseMapper = new FoursquareResponseMapper();
    private String source = "FourSquare";

    @Test
    public void shouldHaveFourSquareAsSource() {
        FourSquareResponse foursquareResponse = getFoursquareResponse();
        LocationInfo locationInfo = foursquareResponseMapper.mapFrom(foursquareResponse, source);

        assertEquals(source, locationInfo.getSource());
    }


    @Test
    public void shouldMapDetailFromGeoCode() {
        FourSquareResponse foursquareResponse = getFoursquareResponse();

        LocationInfo locationInfo = foursquareResponseMapper.mapFrom(foursquareResponse, "Foursquare");

        assertEquals("IN", locationInfo.getCountryCode());
        assertEquals("Pune", locationInfo.getName());
        assertEquals("Pune, Mahārāshtra, India", locationInfo.getDisplayName());

        assertEquals(18.51957, locationInfo.getLatitude(), 0.0);
        assertEquals(73.85535, locationInfo.getLongitude(), 0.0);

        List<Nearby> nearby = locationInfo.getNearby();

        assertEquals(3, nearby.size());
        assertEquals(new Nearby("Shaniwar Wada", "Shaniwar Peth (Bajirao Road)", "Historic Site"), nearby.get(0));
        assertEquals(new Nearby("Cafe Peshwai", "Pune 411030", "Café"), nearby.get(1));
        assertEquals(new Nearby("Implala Hair Saloon", "opposite lal mahal (ganesh road)", "Salon / Barbershop"), nearby.get(2));
    }

    private FourSquareResponse getFoursquareResponse() {
        FourSquareResponse fourSquareResponse = new FourSquareResponse();
        Response response = new Response();

        response.setVenues(getVenues());

        Map<String, Object> geoCode = new HashMap<>();

        geoCode.put("feature", getFeature());

        response.setAdditionalProperty("geocode", geoCode);
        fourSquareResponse.setResponse(response);
        return fourSquareResponse;
    }

    private List<Venue> getVenues() {
        Venue venue1 = getVenue("Shaniwar Wada", "Shaniwar Peth (Bajirao Road)", "Historic Site");
        Venue venue2 = getVenue("Cafe Peshwai", "Pune 411030", "Café");
        Venue venue3 = getVenue("Implala Hair Saloon", "opposite lal mahal (ganesh road)", "Salon / Barbershop");
        return Arrays.asList(venue1, venue2, venue3);
    }

    private Venue getVenue(String name, String address, String categoryName) {
        Venue venue = new Venue();
        venue.setName(name);

        Location location = new Location();
        location.setFormattedAddress(singletonList(address));
        venue.setLocation(location);

        Category category = new Category();
        category.setName(categoryName);
        venue.setCategories(singletonList(category));

        return venue;
    }

    private Map<String, Object> getFeature() {
        Map<String, Object> feature = new HashMap<>();
        feature.put("cc", "IN");
        feature.put("name", "Pune");
        feature.put("displayName", "Pune, Mahārāshtra, India");

        HashMap<String, Object> center = new HashMap<>();
        center.put("lat", 18.51957);
        center.put("lng", 73.85535);

        HashMap<String, Object> geometry = new HashMap<>();
        geometry.put("center", center);

        feature.put("geometry", geometry);
        return feature;
    }

}