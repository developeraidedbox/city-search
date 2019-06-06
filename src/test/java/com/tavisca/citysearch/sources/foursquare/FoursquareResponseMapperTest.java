package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.sources.foursquare.models.FourSquareResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoursquareResponseMapperTest {

    private FoursquareResponseMapper foursquareResponseMapper = new FoursquareResponseMapper();
    private String source = "FourSquare";

    @Test
    public void shouldHaveFourSquareAsSource() {
        FourSquareResponse foursquareResponse = getFoursquareResponse();
        LocationInfo locationInfo = foursquareResponseMapper.mapFrom(foursquareResponse, source);

        assertEquals(source, locationInfo.getSource());
    }

    private FourSquareResponse getFoursquareResponse() {
        FourSquareResponse fourSquareResponse = new FourSquareResponse();
        return fourSquareResponse;
    }

}