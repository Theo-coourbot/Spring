package com.tpAnnonce.annonce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idAnnouncement;
    String title;
    String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "annoucement_category",
            joinColumns = @JoinColumn(name = "annoucement_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    List<Category> categories;

    @OneToMany(mappedBy = "announcement",cascade = CascadeType.DETACH)
    List<Picture> pictures;

}
