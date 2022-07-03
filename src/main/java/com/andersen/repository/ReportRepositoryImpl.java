package com.andersen.repository;

import com.andersen.dto.SingleReport;
import com.andersen.dto.TeamReport;
import com.andersen.model.Group;
import com.andersen.model.Track;
import com.andersen.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {

    private SessionFactory sessionFactory;
    private UserRepository userRepository;

    @Override
    public List<TeamReport> getReports() {
        try (Session session = sessionFactory.openSession()) {
            return userRepository
                    .findAll()
                    .stream()
                    .collect(Collectors.groupingBy(User::getGroup)) //группируем пользователей по группе
                    .entrySet()
                    .stream()
                    .map(this::groupToTeamReport) //из группы превращаем в ДТО
                    .collect(Collectors.toList());
        }
    }

    private TeamReport groupToTeamReport(java.util.Map.Entry<Group, List<User>> x) {
        return new TeamReport(x.getKey().getColor(),
                x.getValue().stream().map(this::userToSingleReport).collect(Collectors.toList()));
    }

    private SingleReport userToSingleReport(User y) {
        return new SingleReport(y.getFirstName(),
                y.getLastName(),
                y.getTracks().stream().map(Track::getText).collect(Collectors.toList()));
    }
}