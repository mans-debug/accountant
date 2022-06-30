package com.andersen.servlet;

import com.andersen.dto.TrackDto;
import com.andersen.service.TrackService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Long.parseLong;

@WebServlet("/tracks")
public class TrackServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private TrackService trackService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        TrackService trackService = (TrackService) config.getServletContext().getAttribute("trackService");
        objectMapper = (ObjectMapper) config.getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        if ((id = getId(req, "userId")) == null) {
            return; //todo response with error
        }
        try (PrintWriter writer = resp.getWriter()) {
            List<TrackDto> responseObj = trackService.getByUser(id);
            objectMapper.writeValue(writer, responseObj);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            TrackDto newTrack = objectMapper.readValue(req.getReader(), TrackDto.class);
            TrackDto created = trackService.create(newTrack);
            objectMapper.writeValue(writer, created);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            TrackDto toUpdate = objectMapper.readValue(req.getReader(), TrackDto.class);
            TrackDto updated = trackService.update(toUpdate);
            objectMapper.writeValue(writer, updated);
        }
    }

    private Long getId(HttpServletRequest req, String parameterName) {
        String textId = null;
        if ((textId = req.getParameter(parameterName)) == null) {
            return null;
        } else {
            return parseLong(textId);
        }
    }
}
