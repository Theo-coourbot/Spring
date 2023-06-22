package com.m2i.cda.exoSession.service;

import com.m2i.cda.exoSession.entity.Produit;

import java.util.List;

public interface IProduitService {

    boolean create(Produit p);
    boolean update(Produit p);

    boolean delete(Produit p);

    Produit findById(int id);

    List<Produit> findAll();




}
