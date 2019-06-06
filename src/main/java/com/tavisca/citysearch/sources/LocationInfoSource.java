package com.tavisca.citysearch.sources;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;

@FunctionalInterface
public interface LocationInfoSource {

    default String getName() {
        return "Unknown Source";
    }

    LocationInfo getLocationInfo(LocationSearchRequest request);
}
