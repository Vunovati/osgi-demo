package com.doma.vlado.bookshelf.inventory.api;

/**
 * Created with IntelliJ IDEA.
 * User: ovca
 * Date: 06.07.13.
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public interface Book {
    String getIsbn();
    String getTitle();
    String getAuthor();
    String getCategory();
    int getRating();
}
