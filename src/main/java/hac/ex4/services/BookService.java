package hac.ex4.services;

import hac.ex4.repo.Book;
import hac.ex4.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * service for book
 */
@Transactional
@Service
public class BookService {

    /**
     * book repository
     */
    @Autowired
    private BookRepository repository;

    /**
     * function to add books to database
     * @param books list of books to add
     */
    public void addBooks(ArrayList<Book> books) {
        for (Book book : books) {
            repository.save(book);
        }
    }

    /**
     * function to save book to database
     * @param book book to save
     */
    public void saveBook(Book book) {
        repository.save(book);
    }

    /**
     * function to delete book by id from database
     * @param id id of book to delete
     */
    public void deleteBook(long id) {
        repository.deleteById(id);
    }

    /**
     * function to delete book by book object from database
     * @param book book object to delete
     */
    public void deleteBook(Book book) {
        repository.delete(book);
    }

    /**
     * function to update some book
     * @param book book to update
     */
    public void updateBook(Book book) {
        repository.save(book);
    }

    /**
     * function to get book from data base by id
     * @param id id of book to get
     * @return book object
     */
    public Optional<Book> getBook(long id) {
        return repository.findById(id);
    }

    /**
     * function to get all books from database
     * @return list of books
     */
    public List<Book> getBooks() {
        return repository.findAll();
    }

    /**
     * get list of books from database by name\some string
     * @param name name of book to get
     * @return list of books
     */
    public List<Book> getBooksByName(String name) {
        return repository.findByBookName(name);
    }

    /**
     * function to get 5 books from database by best discount
     * @return list of top 5 books with the best discount
     */
    public List<Book> getBooksByDiscount() {
        return repository.findTop5ByOrderByDiscountDesc();
    }

    /**
     * function to get books that contain some string in name
     * @param name string to search in database
     * @return list of books
     */
    public List<Book> getBooksByNameContaining(String name) {
        return repository.findByBookNameContaining(name);
    }

    /**
     * function to get all books from database sorted by name
     * @return list of books
     */
    public List<Book> getBooksSortByName() {
        return repository.findByOrderByBookName();
    }

    /**
     * function to handle check out and finish the process of shopping cart
     * @param cart shopping cart to check out
     */
    public void checkOut(Map<Book, Integer> cart) {
        for (Map.Entry<Book, Integer> entry : cart.entrySet()) {
            Book book = entry.getKey();
            if (book.getQuantity() - entry.getValue() < 0) {
                throw new IllegalArgumentException("Sorry, there are "+ book.getQuantity() +" units left from the requested book ("+book.getBookName() +")\n" +
                        "Please update your shopping cart");
            }
            //update quantity of book in database
            book.setQuantity(book.getQuantity() - entry.getValue());
            repository.save(book);
        }
    }

}
