package com.andersen.servlet;

import com.andersen.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {
    private ReportService reportService;
    private ObjectMapper objectMapper;
    @Override
    public void init(ServletConfig config) throws ServletException {
        reportService = ((ReportService) config.getServletContext().getAttribute("reportService"));
        objectMapper = ((ObjectMapper) config.getServletContext().getAttribute("objectMapper"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            objectMapper.writeValue(writer, reportService.getReports());
        }
    }
}
