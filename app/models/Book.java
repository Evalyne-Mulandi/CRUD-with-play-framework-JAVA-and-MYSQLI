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



    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +

                '}';
    }
    public static Finder<Long, Book> find = new Finder<>(Book.class);



}