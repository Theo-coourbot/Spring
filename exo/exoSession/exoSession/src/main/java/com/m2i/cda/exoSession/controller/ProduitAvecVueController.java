package com.m2i.cda.exoSession.controller;


import com.m2i.cda.exoSession.entity.Produit;
import com.m2i.cda.exoSession.service.IProduitService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProduitAvecVueController {


    // Injection de l'independance
    @Autowired
    private IProduitService _produitService;

    @Autowired
    private HttpSession _httpSession;

    @GetMapping("/connexion")
    public String afficherFormulaireConnexion(Model model) {

        return "formulaireConnexion";
    }

    @GetMapping("/add/connexion")
    public String conexion(@RequestParam("name") String name,@RequestParam("password") String password , Model model) {
        _httpSession.setMaxInactiveInterval(60);
        _httpSession.setAttribute(("name"),  name);
        _httpSession.setAttribute(("password"), password);


        return "redirect:/product";
    }


    @GetMapping("")
    public ModelAndView getProduits() {
        ModelAndView modelAndView = new ModelAndView();
        String test = _httpSession.getAttribute("name").toString();
        System.out.println(test);
        String test2 = _httpSession.getAttribute("password").toString();
        System.out.println(test2);
        if (_httpSession.getAttribute("name").toString().equals("theo") && _httpSession.getAttribute("password").toString().equals("test")) {
            if (_produitService.findAll().isEmpty()) {
                modelAndView.setViewName("error");
            } else {
                modelAndView.setViewName("produits");
                modelAndView.addObject("produits", _produitService.findAll());
            }
            return modelAndView;
        } else {
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }


    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable("id") Integer id) {
        return _produitService.findById(id);
    }

    @GetMapping("/search")
    public String searchProductById(@RequestParam("productId") Integer productId, Model model) {
        Produit produit = _produitService.findById(productId);
        model.addAttribute("produit", produit);
        return "product-details";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") Integer id) {
        Produit p = _produitService.findById(id);
        if (p != null && _produitService.delete(p)) {
            return "redirect:/product";
        }
        return "Aucun produit avec cet id";
    }


    @GetMapping("/form")
    public String afficherFormulaireCreationProduit(Model model) {
        model.addAttribute("produit", new Produit());
        return "formulaire";
    }


    @PostMapping("/create")
    public String postProduit(@ModelAttribute Produit produit) {

        System.out.println("produit " + produit);
        if (produit.getId() == null) {
            if (_produitService.create(produit)) {
                return "redirect:/product";
            }
            return "product/error";

        } else {
            Produit existProduit = _produitService.findById(produit.getId());
            if (existProduit != null) {
                existProduit.setDateAchat(produit.getDateAchat());
                existProduit.setMarque(produit.getMarque());
                existProduit.setReference(produit.getReference());
                existProduit.setStock(produit.getStock());
                existProduit.setPrix(produit.getPrix());
                if (_produitService.update(existProduit)) {
                    return "redirect:/product";
                }
            }

            return "product/error";
        }
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {
        Produit pr = _produitService.findById(id);
        System.out.println("pr " + pr);
        model.addAttribute("produit", pr);


        return "formulaire";
    }


    @PostMapping("/update/{id}")
    public Produit updateProduit(@PathVariable("id") Integer id, @RequestBody Produit produit) {
        Produit existProduit = _produitService.findById(id);
        if (existProduit != null) {
            existProduit.setDateAchat(produit.getDateAchat());
            existProduit.setMarque(produit.getMarque());
            existProduit.setReference(produit.getReference());
            existProduit.setStock(produit.getStock());
            existProduit.setPrix(produit.getPrix());
            if (_produitService.update(existProduit)) {
                return existProduit;
            }
        }
        return existProduit;
    }


}
