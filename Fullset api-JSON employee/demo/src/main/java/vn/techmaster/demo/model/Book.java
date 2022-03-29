package vn.techmaster.demo.model;
// Có thể dùng lombok để rút ngắn lại
public class Book {    
    int id;
    String title;
    String description;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Book(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    @Override
    public String toString() {
        return "Book [description=" + description + ", id=" + id + ", title=" + title + "]";
    }
}