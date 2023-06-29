package com.tpAnnonce.annonce.controller;

import com.tpAnnonce.annonce.entity.User;
import com.tpAnnonce.annonce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService _userService;

    @Autowired
    HttpServletResponse _response;

    @Autowired
    HttpSession _session;

    @GetMapping("/")
    public ModelAndView displayFormLog(){
        ModelAndView mv = new ModelAndView("log-form");
        return mv;

    }

    @PostMapping("login-or-signUP")
    public ModelAndView submitLog(@RequestParam String password, @RequestParam String pseudo, @RequestParam String btn) throws Exception {
        User user = new User(pseudo,password);
        ModelAndView mv = new ModelAndView();
        if (btn.equals("signIn")){
           if (  _userService.login(user)){

            mv.setViewName("accueil");
           }
        } else  if (btn.equals("signUp")) {

            if (_userService.signUp(user)){
              mv.setViewName("accueil");
            }

        } else {
            mv.setViewName("log-form");
            throw new Exception("probleme lors de la connexion ou de l'inscription");
        }
        System.out.println(mv);

        return mv;
    }
}
