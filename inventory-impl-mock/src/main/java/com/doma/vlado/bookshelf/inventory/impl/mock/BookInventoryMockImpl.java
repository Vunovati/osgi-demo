package com.doma.vlado.bookshelf.inventory.impl.mock;

import com.doma.vlado.bookshelf.inventory.api.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ovca
 * Date: 06.07.13.
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class BookInventoryMockImpl implements BookInventory {
    public static final String DEFAULT_CATEGORY = "default";

    private Map<String, MutableBook> booksByIsbn = new Hashtable<String, MutableBook>();
    private Map<String, Integer> categories =
            new HashMap<String, Integer>();


    public Set<String> getCategories() {
        return this.categories.keySet();
    }

    public MutableBook createBook(String isbn) throws BookAlreadyExistsException {
        return new MutableBookImpl(isbn);
    }

    public MutableBook loadBookForEdit(String isbn) throws BookNotFoundException {
        MutableBook book = this.booksByIsbn.get(isbn);
        if (book == null) {
            throw new BookNotFoundException(isbn);
        }
        return book;
    }

    public String storeBook(MutableBook book) throws InvalidBookException {
        String isbn = book.getIsbn();
        if (isbn == null) {
            throw new InvalidBookException("ISBN is not set");
        }
        this.booksByIsbn.put(isbn, book);
        String category = book.getCategory();
        if (category == null) {
            category = DEFAULT_CATEGORY;
        }
        if (this.categories.containsKey(category)) {
            int count = this.categories.get(category);
            this.categories.put(category, count + 1);
        } else {
            this.categories.put(category, 1);
        }
        return isbn;
    }

    public Book loadBook(String isbn) throws BookNotFoundException {
        return loadBookForEdit(isbn);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeBook(String isbn) throws BookNotFoundException {
        Book book = this.booksByIsbn.remove(isbn);
        if (book == null) {
            throw new BookNotFoundException(isbn);
        }
        String category = book.getCategory();
        int count = this.categories.get(category);
        if (count == 1) {
            this.categories.remove(category);
        } else {
            this.categories.put(category, count - 1);
        }
    }

    public Set<String> searchBooks(Map<SearchCriteria, String> criteria) {
        {
            LinkedList<Book> books = new LinkedList<Book>();
            books.addAll(this.booksByIsbn.values());
            for (Map.Entry<SearchCriteria, String> criterion
                    : criteria.entrySet()) {
                Iterator<Book> it = books.iterator();
                while (it.hasNext()) {
                    Book book = it.next();
                    switch (criterion.getKey()) {
                        case AUTHOR_LIKE:
                            if (
                                    !checkStringMatch(book.getAuthor(), criterion.getValue())) {
                                it.remove();
                                continue;
                            }
                            break;
                        case ISBN_LIKE:
                            if (!checkStringMatch(
                                    book.getIsbn(), criterion.getValue())) {
                                it.remove();
                                continue;
                            }
                            break;
                        case CATEGORY_LIKE:
                            if (!checkStringMatch(
                                    book.getCategory(), criterion.getValue())) {
                                it.remove();
                                continue;
                            }
                            break;
                        case TITLE_LIKE:
                            if (!checkStringMatch(
                                    book.getTitle(), criterion.getValue())) {
                                it.remove();
                                continue;
                            }
                            break;
                        case RATING_GT:
                            if (!checkIntegerGreater(
                                    book.getRating(), criterion.getValue())) {
                                it.remove();
                                continue;
                            }
                            break;
                        case RATING_LT:
                            if (!checkIntegerSmaller(
                                    book.getRating(), criterion.getValue())) {
                                it.remove();
                                continue;
                            }
                            break;
                    }
                }
            }
            // copy ISBNs
            HashSet<String> isbns = new HashSet<String>();
            for (Book book : books)

            {
                isbns.add(book.getIsbn());
            }

            return isbns;
        }
    }
    private boolean checkIntegerGreater(int attr, String critVal)
    {
        int critValInt;
        try {
            critValInt = Integer.parseInt(critVal);
        }
        catch (NumberFormatException e) {
            return false;
        }
        if (attr >= critValInt) {
            return true;
        }
        return false;
    }

    private boolean checkIntegerSmaller(int attr, String critVal)
    {
        int critValInt;
        try {
            critValInt = Integer.parseInt(critVal);
        }
        catch (NumberFormatException e) {
            return false;
        }
        if (attr >= critValInt) {
            return false;
        }
        return true;
    }

    private boolean checkStringMatch(String attr, String critVal)
    {
        if (attr == null) {
            return false;
        }
        attr = attr.toLowerCase();
        critVal = critVal.toLowerCase();
        boolean startsWith = critVal.startsWith("%");
        boolean endsWith = critVal.endsWith("%");
        if (startsWith && endsWith) {
            if (critVal.length()==1) {
                return true;
            }
            else {
                return attr.contains(
                        critVal.substring(1, critVal.length() - 1));
            }
        }
        else if (startsWith) {return attr.endsWith(critVal.substring(1));
        }
        else if (endsWith) {
            return attr.startsWith(
                    critVal.substring(0, critVal.length() - 1));
        }
        else {
            return attr.equals(critVal);
        }
    }
}

