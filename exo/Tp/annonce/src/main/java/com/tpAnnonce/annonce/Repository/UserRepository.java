package com.tpAnnonce.annonce.Repository;

import com.tpAnnonce.annonce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Integer> {

//    public User findByIdUser(int id);
    public User findByPassword(String password);
    public User findByPseudo(String pseudo);
}
