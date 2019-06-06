package com.tavisca.citysearch.sources;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;

public interface ResponseFilter <V> {
    LocationInfo filter(LocationSearchRequest request, V response);
}
