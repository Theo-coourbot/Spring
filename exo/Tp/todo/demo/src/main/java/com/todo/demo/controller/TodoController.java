package com.todo.demo.controller;


import com.todo.demo.entity.Todo;
import com.todo.demo.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoServiceImpl _todoService;

    @GetMapping("/form")
    public String displayForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoForm";
    }
    @PostMapping("/createOrUpdate")
    public  String postOrUpdatetodo(@ModelAttribute Todo todo) {

        if (todo.getId() == null) {
           if (_todoService.create(todo)){
               return "redirect:/todo";
           }
               return "todo/error";

        } else {
            Todo todoUpdate = _todoService.findById(todo.getId());
            if (todoUpdate !=null) {
                todoUpdate.setDate(todo.getDate());
                todoUpdate.setTitle(todo.getTitle());
                todoUpdate.setDescription(todo.getDescription());
                if (_todoService.update(todoUpdate)){
                    return "redirect:/todo";
                }

            }
        }
        return "todo/error";
    }

    @GetMapping("")
    public ModelAndView getTodos(){
        ModelAndView modelAndView =new ModelAndView();

        if (_todoService.findAll().isEmpty()){
            modelAndView.setViewName("error");
        }else {
            modelAndView.setViewName("todos");
            modelAndView.addObject("todos", _todoService.findAll());
        }
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {
        Todo tr = _todoService.findById(id);

        model.addAttribute("todo", tr);


        return "todoForm";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") Integer id) {
        Todo t = _todoService.findById(id);
        if (t != null && _todoService.delete(t)) {
            return "redirect:/todo";
        }
        return "Aucun todo avec l'id";
    }
    @GetMapping("details/{id}")
    public String todoById(@PathVariable("id") Integer id ,Model model) {

        Todo todo = _todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todoDetail";
    }
    @GetMapping("changeStatus/{id}")
    public String todoupdateStatus(@PathVariable("id") Integer id) {

        Todo todo = _todoService.findById(id);
        todo.setDone(!todo.isDone());
        _todoService.changeState(todo);

        return "redirect:/todo";
    }
}
