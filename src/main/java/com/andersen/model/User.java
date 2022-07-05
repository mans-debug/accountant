package com.andersen.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    private Long telegramId;

    private String firstName;

    private String lastName;

    private Date lastModified;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @EqualsAndHashCode.Exclude
    private Group group;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    List<Track> tracks;
}