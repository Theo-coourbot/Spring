package com.m2i.cda.lille.controller;


import com.m2i.cda.lille.entities.Produit;
import com.m2i.cda.lille.services.ProduitService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;



    @RequestMapping("/")
    public ModelAndView getHome(){

        ModelAndView modelAndView = new ModelAndView();


            if(produitService.findAll().isEmpty()){
                modelAndView.setViewName("error");
            }else{
                modelAndView.setViewName("produits");
                modelAndView.addObject("produits",produitService.findAll());
            }
            return modelAndView;

    }
    @GetMapping("/formUpdate")
    public ModelAndView updateProduitForm(@RequestParam("produit") Integer produitId){
        ModelAndView modelAndView = new ModelAndView();
        Produit produit = produitService.findById(produitId);
        modelAndView.addObject("produit", produit);
        modelAndView.setViewName("formAddProduit");
        return modelAndView;

    }

    @GetMapping("/formAddProduit")
    public ModelAndView addProduitForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("produit", new Produit());
        modelAndView.setViewName("formAddProduit");
        return modelAndView;
    }

    @GetMapping("/produit")
    public ModelAndView searchProductById(@RequestParam("produitId") Integer produitId, Model model) {
        Produit produit = produitService.findById(produitId);
        model.addAttribute("produit", produit);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("produitDetail");
        return modelAndView;
    }


    @RequestMapping(value = "/produit/delete",method = {RequestMethod.DELETE, RequestMethod.GET})
        public String deleteProduit(@RequestParam("produitId") Integer produitId){

           Produit produit =produitService.findById(produitId);

           produitService.delete(produit);
            return "redirect:/";
        }



        @PutMapping(value = "/produit/update/{data}")
        public String updateProduit(@ModelAttribute Produit produitactu){

           Produit produit = produitService.findById(produitactu.getId());
            produit.setMarque(produitactu.getMarque());
            produit.setPrix(produitactu.getPrix());
            produit.setReference(produitactu.getReference());
            produit.setStock(produitactu.getStock());
           produitService.update(produit);
            return "redirect:/";
        }







    @PostMapping("/post/produit")
    public String pagePost(@ModelAttribute Produit produit) {
        produitService.create(produit);
        return "redirect:/";
    }
}
