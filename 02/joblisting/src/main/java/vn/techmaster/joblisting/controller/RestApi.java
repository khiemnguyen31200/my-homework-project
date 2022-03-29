package vn.techmaster.joblisting.controller;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.joblisting.dto.JobRequest;
import vn.techmaster.joblisting.model.Job;

@RestController
@RequestMapping("")
public class RestApi {
  private ConcurrentHashMap<String, Job> jobs;
  public RestApi() {
    jobs = new ConcurrentHashMap<>();
    jobs.put("DS01", new Job("DS01", "IT", "123", "HN", 1000, 5000, "123@gmail.com"));
    jobs.put("DS02", new Job("DS02", "DT", "123", "HCM", 500, 5000, "123@gmail.com"));
    jobs.put("DS03", new Job("DS03", "IT", "123", "HN", 500, 5000, "123@gmail.com"));
  }

  @GetMapping
  public List<Job> getJobs() {
    return jobs.values().stream().sorted(Comparator.comparing(Job::getMinSalary)).toList();
  }

  @GetMapping("/job/sortbylocation")
  public List<Job> getJobsByLocation() {
    return jobs.values().stream().sorted(Comparator.comparing(Job::getLocation)).toList();
  }

  @GetMapping("/job/salary/{salary}")
  public List<Job> getJobsBySalary(@PathVariable("salary") int salary) {
    return jobs.values().stream().filter(job -> job.matchWithMoney(salary)).collect(Collectors.toList());
  }

  @GetMapping("/job/keyword/{keyword}")
  public List<Job> getJobsBySearch(@PathVariable("keyword") String keyword) {
    return jobs.values().stream().filter(job -> job.matchWithKeyword(keyword)).collect(Collectors.toList());
  }

  @GetMapping("/job/keyword/{title}/{detail}")
  public List<Job> getJobsBySearchDetail(@PathVariable("title") String title,
      @PathVariable("detail") String description) {
    return jobs.values().stream().filter(job -> job.matchWithTitleDescriptionKeyword(title, description))
        .collect(Collectors.toList());
  }

  @PostMapping
  public Job createNewJob(@RequestBody JobRequest jobrequest) {
    String uuid = UUID.randomUUID().toString();
    Job newJob = new Job(uuid, jobrequest.title(), jobrequest.description(), jobrequest.location(),
        jobrequest.minSalary(), jobrequest.maxSalary(), jobrequest.email());
    jobs.put(uuid, newJob);
    return newJob;
  }

  @GetMapping(value = "/{id}")
  public Job getBookById(@PathVariable("id") String id) {
    return jobs.get(id);
  }

  @PutMapping(value = "/{id}")
  public Job updateBookById(@PathVariable("id") String id, @RequestBody JobRequest jobrequest) {
    Job updateJob = new Job(id, jobrequest.title(), jobrequest.description(), jobrequest.location(),
        jobrequest.minSalary(), jobrequest.maxSalary(), jobrequest.email());
    jobs.replace(id, updateJob);
    return updateJob;
  }

  @DeleteMapping(value = "/{id}")
  public Job deleteBookById(@PathVariable("id") String id) {
    Job removedjob = jobs.remove(id);
    return removedjob;
  }
}
