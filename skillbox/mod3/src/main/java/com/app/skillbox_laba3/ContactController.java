package com.app.skillbox_laba3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactDao contactDao;

    @GetMapping
    public String getAll(Model model) {
        List<Contact> products = contactDao.findAll();
        model.addAttribute("contacts", products);
        return "contacts/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contacts/form";
    }

    @PostMapping
    public String update(@ModelAttribute Contact contact) {
        if (contact.getId() != null) {
            contactDao.update(contact);
        } else {
            contactDao.save(contact);
        }
        return "redirect:/contacts";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactDao.findById(id);
        model.addAttribute("contact", contact);
        return "contacts/form";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        contactDao.deleteById(id);
        return "redirect:/contacts";
    }

}
