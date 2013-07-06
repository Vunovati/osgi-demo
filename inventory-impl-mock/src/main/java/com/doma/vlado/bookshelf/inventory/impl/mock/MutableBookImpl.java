package com.doma.vlado.bookshelf.inventory.impl.mock;

import com.doma.vlado.bookshelf.inventory.api.MutableBook;

/**
 * Created with IntelliJ IDEA.
 * User: ovca
 * Date: 06.07.13.
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class MutableBookImpl implements MutableBook {
    private String isbn;
    private String author;
    private String title;
    private String category;
    private int rating;

    public MutableBookImpl(String isbn) {
        this.isbn = isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(String rating) {
        this.rating = Integer.valueOf(rating);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "MutableBookImpl{" +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                '}';
    }
}
