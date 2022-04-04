package vn.techmasterr.jobhunt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {
    private String id;
    private String name;
    private String website;
    private String email;
    private String address;
}
