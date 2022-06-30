package com.andersen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "team_schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long telegramId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastModified;

    @OneToMany(mappedBy = "user")
    private List<Track> tracks;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
