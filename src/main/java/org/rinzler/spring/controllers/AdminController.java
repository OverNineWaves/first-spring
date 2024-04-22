package org.rinzler.spring.controllers;

import org.rinzler.spring.DAO.PersonDAO;
import org.rinzler.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Component
@RequestMapping("/admin")
public class AdminController {
    PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showList(Model model, @ModelAttribute ("person") Person person){
        model.addAttribute("people", personDAO.index());
        return "admin/list";
    }

    @PatchMapping("/add")
    public String takeFromList(@ModelAttribute ("person") Person person){
        System.out.println("Person id: " + person.getId());
        return "redirect:/admin";
    }
}
