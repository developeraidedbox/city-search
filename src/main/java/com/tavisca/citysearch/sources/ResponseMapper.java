package com.tavisca.citysearch.sources;

import com.tavisca.citysearch.models.LocationInfo;

@FunctionalInterface
public interface ResponseMapper<T> {
    LocationInfo mapFrom(T response);
}