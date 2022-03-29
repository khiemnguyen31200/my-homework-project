package vn.techmaster.joblisting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private String id;
    private String title;
    private String description;
    private String location;
    private int minSalary;
    private int maxSalary;
    private String email;

    public boolean matchWithKeyword(String keyword) {
        return (title.toLowerCase().contains(keyword.toLowerCase())
                || description.toLowerCase().contains(keyword.toLowerCase()));
    }

    public boolean matchWithTitleDescriptionKeyword(String titleSrc, String descriptionSrc) {
        return (title.toLowerCase().contains(titleSrc.toLowerCase())
                && description.toLowerCase().contains(descriptionSrc.toLowerCase()));
    }

    public boolean matchWithMoney(int money) {
        return (minSalary < money && maxSalary > money);
    }

}
