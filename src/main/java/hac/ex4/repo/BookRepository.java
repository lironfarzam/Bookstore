package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * BookRepository class
 * default scope of this Bean is "singleton"
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * query to get all books from database by name
     * @param bookName name of book to get
     * @return list of books with given name
     */
    List<Book> findByBookName(String bookName);
    //find the 5 books with the highest discount

    /**
     * query to get best 5 discount books from database
     * @return list of books with the highest discount
     */
    List<Book> findTop5ByOrderByDiscountDesc();

    /**
     * query to get all books from database that conatins given keyword
     * @param bookName keyword to search for
     * @return list of books that contains given keyword
     */
    List<Book> findByBookNameContaining(String bookName);

    /**
     * query to get all books from database sorted by name
     * @return list of books sorted by name
     */
    List<Book> findByOrderByBookName();

}
