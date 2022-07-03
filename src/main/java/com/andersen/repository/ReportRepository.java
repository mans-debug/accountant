package com.andersen.repository;

import com.andersen.dto.TeamReport;

import java.util.List;

public interface ReportRepository {

    List<TeamReport> getReports();
}