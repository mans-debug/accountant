package com.andersen.listener;


import com.andersen.dto.DtoMapper;
import com.andersen.repository.*;
import com.andersen.service.ReportService;
import com.andersen.service.ReportServiceImpl;
import com.andersen.service.TrackService;
import com.andersen.service.TrackServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mapstruct.factory.Mappers;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {
    private SessionFactory sessionFactory;

    private SessionFactory sessionFactory() {
        return new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DtoMapper dtoMapper = Mappers.getMapper(DtoMapper.class);
        sessionFactory = sessionFactory();
        ObjectMapper objectMapper = new ObjectMapper();

        TrackRepository trackRepository = new TrackRepositoryImpl(sessionFactory);
        UserRepository userRepository = new UserRepositoryImpl(sessionFactory);
        ReportRepository reportRepository = new ReportRepositoryImpl(sessionFactory, userRepository);


        TrackService trackService = new TrackServiceImpl(dtoMapper,
                trackRepository,
                userRepository);
        ReportService reportService = new ReportServiceImpl(reportRepository);

        servletContextEvent.getServletContext().setAttribute("trackService", trackService);
        servletContextEvent.getServletContext().setAttribute("reportService", reportService);
        servletContextEvent.getServletContext().setAttribute("objectMapper", objectMapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        sessionFactory.close();
    }
}