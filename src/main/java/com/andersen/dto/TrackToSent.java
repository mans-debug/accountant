package com.andersen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackToSent {
    private Long id;
    private Long userId;
    private String description;
    private double spentHours;
}
