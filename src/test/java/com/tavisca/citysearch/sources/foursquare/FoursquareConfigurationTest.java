package com.tavisca.citysearch.sources.foursquare;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoursquareConfigurationTest {

    private String url = "https://baseUrl";
    private String client_id = "some_client_id";
    private String client_secret = "some_client_secret";
    private String version = "some_version";
    private String intent = "some_intent";
    private String near = "Pune";

    @Test
    public void shouldBuildUrlFromRequiredFields() {
        FoursquareConfiguration configuration = new FoursquareConfiguration(url, client_id, client_secret, version, intent);

        assertEquals(
                String.format("%s?client_id=%s&client_secret=%s&v=%s&near=%s&intent=%s", url, client_id, client_secret, version, near, intent)
                , configuration.buildUrl(near));
    }
}