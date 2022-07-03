package com.andersen.service;

import com.andersen.dto.TeamReport;
import com.andersen.repository.ReportRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;
    @Override
    public List<TeamReport> getReports() {
        return reportRepository.getReports();
    }
}
