package com.tavisca.citysearch.models;


import lombok.Data;

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
}