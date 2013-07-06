package com.doma.vlado.bookshelf.inventory.api;

/**
 * Created with IntelliJ IDEA.
 * User: ovca
 * Date: 06.07.13.
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public interface MutableBook extends Book {
    void setIsbn(String isbn);
    void setTitle(String title);
    void setAuthor(String author);
    void setCategory(String category);
    void setRating(String rating);
}
