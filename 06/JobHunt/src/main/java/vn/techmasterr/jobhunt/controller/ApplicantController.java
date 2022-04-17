package vn.techmasterr.jobhunt.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmasterr.jobhunt.model.Applicant;
import vn.techmasterr.jobhunt.repository.ApplicantRepository;
import vn.techmasterr.jobhunt.repository.JobRepository;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
  @Autowired ApplicantRepository applicantRepo;
  @Autowired JobRepository jobRepo;


  @GetMapping("/list")
    public String listAll(Model model) {
      model.addAttribute("applies", applicantRepo.getAll());
      return "allapply";
    }
    @GetMapping(value = "/{id}")
    public String getByID(@PathVariable("id") String id, Model model) {
    Optional<Applicant> applicant = applicantRepo.get(id);
    if (applicant.isPresent()) {
      model.addAttribute("apply", applicant.get());
    }
    return "applicantdetail";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("apply", new Applicant());
    model.addAttribute("jobCode", jobRepo.getAll());
    return "applicantform";
  }

  @PostMapping(value = "/save") //,consumes = {"multipart/form-data}"}
  public String save(@Valid @ModelAttribute("apply") Applicant applicant, BindingResult result, Model model) {//Kết quả nếu thành công trả về success
    if (result.hasErrors()) {
      model.addAttribute("apply", applicant);// Để hứng lỗi sai
      return "applicantform";
    }
   if (applicant.getId()!="") {
    applicantRepo.update(applicant);
   } else {
    applicantRepo.add(applicant);
   }
    return "redirect:/applicant/list";
  }

  @GetMapping(value = "/edit/{id}")
  public String editEmployerById(@PathVariable("id") String id, Model model) {
    Optional<Applicant> applicant = applicantRepo.get(id);
    if (applicant.isPresent()) {
      model.addAttribute("apply", applicant.get());
      model.addAttribute("jobCode", jobRepo.getAll());
    }
    return "applicantform";
  }
  @GetMapping(value = "/delete/{id}")
  public String deleteByID(@PathVariable("id") String id) {
    applicantRepo.deleteByID(id);
    return "redirect:/applicant/list";
  }
}
