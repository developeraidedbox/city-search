package com.tavisca.citysearch.sources;

import com.tavisca.citysearch.models.LocationInfo;

public interface ResponseMapper<T> {
    LocationInfo mapFrom(T response, String source);
}