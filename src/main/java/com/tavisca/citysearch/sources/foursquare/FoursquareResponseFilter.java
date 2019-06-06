package com.tavisca.citysearch.sources.foursquare;

import com.tavisca.citysearch.models.LocationInfo;
import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.models.Nearby;
import com.tavisca.citysearch.sources.ResponseFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoursquareResponseFilter implements ResponseFilter<LocationInfo> {

    @Override
    public LocationInfo filter(LocationSearchRequest request, LocationInfo response) {

        if (isEmpty(request.getCategory()))
            return response;

        LocationInfo newResponse = response.clone();
        List<Nearby> nearBy = newResponse.getNearby();

        List<Nearby> filteredNearBy = nearBy.stream()
                .filter(nearby -> nearby != null && !isEmpty(nearby.getKind()) && nearby.getKind().trim().contains(request.getCategory().trim()))
                .collect(Collectors.toList());
        newResponse.setNearby(filteredNearBy);

        return newResponse;
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
