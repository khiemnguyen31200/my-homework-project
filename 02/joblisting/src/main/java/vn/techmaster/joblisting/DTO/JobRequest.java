package vn.techmaster.joblisting.DTO;

public record JobRequest(String id, String title, String description, String location, int minSalary, int maxSalary, String email) {
    
}
