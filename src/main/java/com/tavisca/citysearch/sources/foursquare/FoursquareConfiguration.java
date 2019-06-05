package com.tavisca.citysearch.sources.foursquare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "foursquare.api")
@EnableConfigurationProperties(FoursquareConfiguration.class)
public class FoursquareConfiguration {
    private String url;
    private String clientId;
    private String clientSecret;
    private String version;
    private String intent;

    public String buildUrl(String near) {
        return url +
                "?client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&v=" + version +
                "&near=" + near +
                "&intent=" + intent;
    }
}
