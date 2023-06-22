package com.m2i.cda.exoSession.entity;


import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String marque;

    private String reference;

    @Temporal(TemporalType.DATE)
    @Column(name="dateAchat")
    private Date dateAchat;

    private double prix;

    private int stock;


}
