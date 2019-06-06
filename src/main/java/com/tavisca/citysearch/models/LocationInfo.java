package com.tavisca.citysearch.models;


import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class LocationInfo implements Cloneable {
    private String name;
    private String source;
    private String countryCode;
    private String displayName;
    private double latitude;
    private double longitude;
    private List<Nearby> nearby;

    public List<Nearby> getNearby() {
        return nearby == null ? Collections.emptyList() : new ArrayList<>(nearby);
    }

    @Override
    public LocationInfo clone() {
        try {
            LocationInfo newLocationInfo = (LocationInfo) super.clone();
            newLocationInfo.setNearby(this.getNearby());
            return newLocationInfo;
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}