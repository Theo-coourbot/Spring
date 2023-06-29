package com.tpAnnonce.annonce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idUser;
    String pseudo;
    String password;

    boolean admin = false;
    boolean ban = false;

    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH)
    List<Announcement> announcementList;
}
