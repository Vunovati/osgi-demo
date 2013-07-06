package com.doma.vlado.bookshelf.inventory.api;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ovca
 * Date: 06.07.13.
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public interface BookInventory {
    enum SearchCriteria {
        ISBN_LIKE,
        TITLE_LIKE,
        AUTHOR_LIKE,
        GROUP_LIKE,
        GRADE_GT,
        GRADE_LT
    }

    Set<String> getCategories();

    MutableBook createBook(String isbn)
            throws BookAlreadyExistsException;

    MutableBook loadBookForEdit(String isbn)
            throws BookNotFoundException;

    String storeBook(MutableBook book) throws InvalidBookException;

    Book loadBook(String isbn) throws BookNotFoundException;

    void removeBook(String isbn) throws BookNotFoundException;

    Set<String> searchBooks(
            Map<SearchCriteria, String> criteria);
}
