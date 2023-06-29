package com.tpAnnonce.annonce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCategory;

    String name;


    @ManyToMany(mappedBy = "categories")
    List<Announcement> announcements;
}
