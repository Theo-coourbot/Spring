package com.tpAnnonce.annonce.service;

import com.tpAnnonce.annonce.Repository.UserRepository;
import com.tpAnnonce.annonce.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    HttpSession _httpSession;

    public boolean signUp(User user) throws Exception {
        if (user.getPseudo() == null || user.getPassword() == null){
            throw new Exception("champ non remplis");
        }
        if (_userRepository.save(user)!= null){
            _httpSession.setAttribute("pseudo",user.getPseudo());
            return true;
        }

        return false;
    }

    public boolean login(User user) throws Exception{
        User userSearch = _userRepository.findByPassword(user.getPassword()) ;
        if (userSearch != null && userSearch.getPassword().equals(user.getPassword())&& userSearch.getPseudo().equals(user.getPseudo())){
           _httpSession.setAttribute("pseudo",userSearch.getPseudo());
            return true;
        } else {
            throw new  Exception ("mauvais mot de passe ou pseudo");

        }
    }

    public boolean changeStatusUser (String pseudo) throws Exception {
        User user = _userRepository.findByPseudo(pseudo);
        if (user != null){
            user.setBan(!user.isBan());
            return true;
        } else {
            throw new Exception("user introuvable");
        }
    }



}
