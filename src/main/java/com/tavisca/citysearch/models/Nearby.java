package com.tavisca.citysearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Nearby {
    private String name;
    private String address;
    private String kind;
}