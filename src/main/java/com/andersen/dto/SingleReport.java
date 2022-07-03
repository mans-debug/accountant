package com.andersen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class SingleReport {

    private String firstName;
    private String lastName;
    List<String> tracks;

}