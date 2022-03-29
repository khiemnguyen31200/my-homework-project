package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.demo.reponsitory.*;

@Controller
@RequestMapping("/")
public class ServerController {
    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/books")
    public String listAll(Model model) {
        model.addAttribute("bookList", bookDAO.getall());
        return "helloworld";
    }
}
