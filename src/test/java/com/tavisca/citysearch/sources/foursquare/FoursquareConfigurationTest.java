package com.tavisca.citysearch.sources.foursquare;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoursquareConfigurationTest {

    @Test
    public void shouldBuildUrlFromRequiredFields(){
        FoursquareConfiguration configuration = new FoursquareConfiguration("https://baseUrl", "some_client_id", "some_client_secret", "some_version", "some_intent");

        assertEquals("https://baseUrl?client_id=some_client_id&client_secret=some_client_secret&v=some_version&near=Pune&intent=some_intent"
                , configuration.buildUrl("Pune"));
    }
}