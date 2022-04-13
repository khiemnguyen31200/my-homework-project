package vn.techmasterr.jobhunt.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import vn.techmasterr.jobhunt.model.Employer;

@Repository
public class EmployerRepository {  
  List<Employer> employerLists = new ArrayList<>();
  public EmployerRepository(){
    employerLists.add(new Employer(UUID.randomUUID().toString(),"TopDEV","topdev.vn","apply@topdev.vn","Ha Noi"));
    employerLists.add(new Employer(UUID.randomUUID().toString(),"ITViec","itviec.com","apply@itviec.com","Ho Chi Minh"));
  }

  public List<Employer> getAll() {
    return employerLists;
  }

  public Optional<Employer> get(String id) {
    return employerLists.stream().filter(u -> u.getId().contains(id)).findFirst();
  }

  public void add(Employer employer) {
    employer.setId(UUID.randomUUID().toString());
    employerLists.add(employer);
  }

  public void update(Employer employer) {
    get(employer.getId()).ifPresent(existemployee -> {
      existemployee.setName(employer.getName());
      existemployee.setWebsite(employer.getWebsite());
      existemployee.setEmail(employer.getEmail());
      existemployee.setAddress(employer.getAddress());
    });
  }

  public void deleteByID(String id) {
    get(id).ifPresent(existemployer -> employerLists.remove(existemployer));
  }

  public void delete(Employer employer) {
    deleteByID(employer.getId());
  }
}
