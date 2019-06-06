package com.tavisca.citysearch.services;


import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.models.LocationSearchResponse;
import com.tavisca.citysearch.sources.LocationInfoSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationSearchService {

    private static final Logger logger = LogManager.getLogger(LocationSearchService.class);

    private final List<LocationInfoSource> locationInfoSources;

    @Autowired
    public LocationSearchService(List<LocationInfoSource> locationInfoSources) {
        this.locationInfoSources = locationInfoSources;
    }

    public LocationSearchResponse search(LocationSearchRequest request) {
        List<LocationInfo> locationInfos = this.locationInfoSources
                .parallelStream()
                .map(source -> source.getLocationInfo(request)).collect(Collectors.toList());

        return new LocationSearchResponse(request.getQuery(), request.getCategory(), locationInfos);
    }
}
