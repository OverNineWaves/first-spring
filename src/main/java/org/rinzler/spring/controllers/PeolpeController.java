package org.rinzler.spring.controllers;

import org.rinzler.spring.DAO.PersonDAO;
import org.rinzler.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PeolpeController {

    private final PersonDAO personDAO;

    @Autowired
    public PeolpeController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String personID(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPersonForm(Model model){
        model.addAttribute("newPerson", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute ("newPerson") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String editPersonForm(Model model, @PathVariable("id") int id){
        model.addAttribute("editPerson", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("editPerson") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
        {
            return "people/edit";
        }
        personDAO.edit(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
