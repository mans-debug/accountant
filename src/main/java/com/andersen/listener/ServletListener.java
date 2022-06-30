package com.andersen.listener;


import com.andersen.dto.DtoMapper;
import com.andersen.repository.TrackRepository;
import com.andersen.repository.TrackRepositoryImpl;
import com.andersen.repository.UserRepository;
import com.andersen.service.TrackService;
import com.andersen.service.TrackServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mapstruct.factory.Mappers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
        UserRepository userRepository = null; //todo

        TrackService trackService = new TrackServiceImpl(dtoMapper,
                trackRepository,
                userRepository);

        servletContextEvent.getServletContext().setAttribute("trackService", trackService);
        servletContextEvent.getServletContext().setAttribute("objectMapper", objectMapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        sessionFactory.close();
    }
}