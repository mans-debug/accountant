package com.andersen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TeamReport {

    private String color;

    List<SingleReport> singleReports;

}