package vn.techmasterr.jobhunt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.repository.EmployerRepository;



@Controller
@RequestMapping("/employer")
public class EmployerController {
  @Autowired EmployerRepository empRepo;

  @GetMapping("/list")
  public String listAll(Model model) {
    model.addAttribute("employers", empRepo.getAll());
    return "allemployer";
  }
  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("employer", new Employer());
    return "employerform";
  }
  @GetMapping(value = "/{id}")
  public String getByID(@PathVariable("id") String id, Model model) {
    Optional<Employer> employer = empRepo.get(id);
    if (employer.isPresent()) {
      model.addAttribute("employer", employer.get());
    }
    return "employerdetail";
  }
  @PostMapping("/save")
  public String save(@ModelAttribute("employer") Employer employer, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("employer", employer);
      return "employerform";
    }
    if (employer.getId()!="") {
        empRepo.update(employer);
    } else {
        empRepo.add(employer);
    }

    return "redirect:/employer/list";
  }
  @GetMapping(value = "/edit/{id}")
  public String editBookId(@PathVariable("id") String id, Model model) {
    Optional<Employer> employer = empRepo.get(id);
    if (employer.isPresent()) {
      model.addAttribute("employer", employer.get());
    }
    return "employerform";
  }

  @GetMapping(value = "/delete/{id}")
  public String deleteByID(@PathVariable("id") String id) {
    empRepo.deleteByID(id);
    return "redirect:/employer/list";
  }
}
