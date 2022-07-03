package com.andersen.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackToSent {
    private Long id;
    private String description;
    private double spentHours;
}
