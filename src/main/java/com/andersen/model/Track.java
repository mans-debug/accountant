package com.andersen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //time when track was sent
    private Date date;
    //time spend on a task
    //better make it long, imho
    private double timeSpent;
    private String text;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
