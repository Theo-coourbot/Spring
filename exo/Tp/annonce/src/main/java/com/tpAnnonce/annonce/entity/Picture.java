package com.tpAnnonce.annonce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPicture;
    String url;

    @ManyToOne
    @JoinColumn(name = "annoucement_id")
    Announcement announcement;





}
