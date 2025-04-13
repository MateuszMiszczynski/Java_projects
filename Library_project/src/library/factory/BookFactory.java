package library.factory;

import library.model.Book;

public class BookFactory {

    public static Book createBook(int id, String title) {
        return new Book(id, title);
    }
}
