package models;

import javax.persistence.*;
import io.ebean.Finder;
import io.ebean.*;


@Entity
@Table(name="book")
public class Book extends  Model {

    @Id
    public Long id;
    public String title;
    public String author;
    public String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return  description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +

                '}';
    }
    public static Finder<Long, Book> find = new Finder<>(Book.class);



}