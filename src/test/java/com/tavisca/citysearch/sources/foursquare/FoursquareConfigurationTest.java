package com.tavisca.citysearch.sources.foursquare;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoursquareConfigurationTest {



    @Test
    public void shouldBuildUrlFromRequiredFields() {

        String url = "https://baseUrl";
        String client_id = "some_client_id";
        String client_secret = "some_client_secret";
        String version = "some_version";
        String intent = "some_intent";
        String near = "Pune";

        FoursquareConfiguration configuration = new FoursquareConfiguration(url, client_id, client_secret, version, intent);

        assertEquals(
                String.format("%s?client_id=%s&client_secret=%s&v=%s&near=%s&intent=%s", url, client_id, client_secret, version, near, intent)
                , configuration.buildUrl(near));
    }
}