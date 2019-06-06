package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.Nearby;
import com.tavisca.citysearch.sources.ResponseMapper;
import com.tavisca.citysearch.sources.foursquare.models.Category;
import com.tavisca.citysearch.sources.foursquare.models.FourSquareResponse;
import com.tavisca.citysearch.sources.foursquare.models.Venue;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FoursquareResponseMapper implements ResponseMapper<FourSquareResponse> {


    @Override
    public LocationInfo mapFrom(FourSquareResponse response, String source) {

        Map<String, Object> geocode = getData(response.getResponse().getAdditionalProperties(), MetaDataField.GEO_CODE);
        Map<String, Object> feature = getData(geocode, MetaDataField.FEATURE);
        Map<String, Object> geometry = getData(feature, MetaDataField.GEO_METRY);
        Map<String, Object> center = getData(geometry, MetaDataField.CENTER);

        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setSource(source);
        locationInfo.setName((String) feature.get(MetaDataField.NAME));
        locationInfo.setCountryCode((String) feature.get(MetaDataField.CC));
        locationInfo.setDisplayName((String) feature.get(MetaDataField.DISPLAY_NAME));
        locationInfo.setLatitude((double) center.get(MetaDataField.LAT));
        locationInfo.setLongitude((double) center.get(MetaDataField.LNG));
        locationInfo.setNearby(buildNearby(response.getResponse().getVenues()));
        return locationInfo;
    }

    private Map<String, Object> getData(Map<String, Object> metaData, String key) {
        return metaData != null && metaData.containsKey(key)
                ? (Map<String, Object>) metaData.get(key)
                : Collections.emptyMap();
    }

    private List<Nearby> buildNearby(List<Venue> venues) {
        return venues.stream().map(this::toNearby).collect(Collectors.toList());
    }

    private Nearby toNearby(Venue venue) {
        return new Nearby(venue.getName(),
                        getFirstOrEmpty(venue.getLocation().getFormattedAddress()),
                                toKind(venue.getCategories()));
    }

    private String toKind(List<Category> categories) {

        if (categories == null || categories.isEmpty())
            return "";

        return categories.stream().map(Category::getName).collect(Collectors.joining(", "));
    }

    private String getFirstOrEmpty(List<String> list) {
        return list.stream().collect(Collectors.joining(", "));
    }

    private static class MetaDataField {

        public static final String GEO_CODE = "geocode";
        public static final String CENTER = "center";
        public static final String NAME = "name";
        public static final String CC = "cc";
        public static final String DISPLAY_NAME = "displayName";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        private static final String FEATURE = "feature";
        private static final String GEO_METRY = "geometry";

        private MetaDataField() {
        }
    }

}
