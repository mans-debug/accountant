package com.andersen.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //todo would be better to make it a constant list
    private String color;

    @OneToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private User teamLead;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "group")
    @EqualsAndHashCode.Exclude
    private List<User> users;


    public Group addUser(User user){
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
        return this;
    }
}