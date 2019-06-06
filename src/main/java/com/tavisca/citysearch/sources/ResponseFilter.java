package com.tavisca.citysearch.sources;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;

public interface ResponseFilter <T> {
    LocationInfo filter(LocationSearchRequest request, T response);
}
