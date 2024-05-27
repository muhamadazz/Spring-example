package com.azz;

import jakarta.persistence.*;

import java.lang.reflect.Constructor;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @SequenceGenerator(
            name = "Book_Id_sequence",
            sequenceName = "Book_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Book_id_sequence"
    )

    private int idBook;
    private String title;
    private String author;
    private String description;
    private String categories;
    private int quantity;
    private int booked;

    public Book() {

    }

    public Book(int idBook, String title, String author, String description, String categories, int quantity, int booked) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.description = description;
        this.categories = categories;
        this.quantity = quantity;
        this.booked = booked;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return idBook == book.idBook && quantity == book.quantity && booked == book.booked && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(description, book.description) && Objects.equals(categories, book.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, title, author, description, categories, quantity, booked);
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", categories='" + categories + '\'' +
                ", quantity=" + quantity +
                ", booked=" + booked +
                '}';
    }
}
