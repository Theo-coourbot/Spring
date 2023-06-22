package com.m2i.cda.lille.controller;

import com.m2i.cda.lille.Entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody
public class PersonController {

    public List<Person> addPerson(){
        Person person = new Person(1,"tot","rober",55);
        Person person1 = new Person(2,"ligne","miche",19);
        Person person2 = new Person(3,"afeu","pierre",30);
        List<Person> personList = Arrays.asList(person,person1,person2);

        return personList;
    }


    @RequestMapping("/")
    public String getHome(){
        List<Person> personList =addPerson();
        String text = "";

        for (Person p : personList){
             text += "nom " +p.getLastName() +" prenom : " + p.getFirstName()+ " age : " + p.getAge() + "<br>";
        }

        return text;
    }




    @PostMapping("/post/personne")
    public String pagePost(@RequestBody Person person ){
        return "nom " +person.getLastName() +" prenom : " + person.getFirstName()+ " age : " + person.getAge() ;
//        List<Person> personList = addPerson();
//        personList.add(person);
//        String text = "";
//        for (Person p : personList){
//            if (p.getId() == person.getId()){
//                text += "nom " +p.getLastName() +" prenom : " + p.getFirstName()+ " age : " + p.getAge() + "<br>";
//                break;
//            }
//        }
//        return text;

    }

    @GetMapping("/personne/{data}")
    public String getData(@PathVariable("data") Integer id){
        List<Person> personList =addPerson();
        String text = "";
        for (Person p : personList){
            if (p.getId() == id){
            text += "nom " +p.getLastName() +" prenom : " + p.getFirstName()+ " age : " + p.getAge() + "<br>";
             break;
            }
        }
        return text;
    }


}
