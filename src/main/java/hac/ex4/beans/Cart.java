package hac.ex4.beans;

import hac.ex4.repo.Book;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Cart class
 */
@Component
public class Cart implements Serializable {

    /**
     * Map of books in cart
     */
    private Map<Book, Integer> cart;

    /**
     * Constructor
     */
    public Cart() {
        this.cart = new HashMap<>();
    }

    /**
     * function to get the cart
     * @return cart Map object
     */
    public Map<Book, Integer> getCart() {
        return this.cart;
    }

    /**
     * function to set new map to cart
     * @param cart Map object with key-book and value-Integer
     */
    public void setCart(Map<Book, Integer> cart) {
        this.cart = cart;
    }

    /**
     * function to reset the cart map
     */
    public void clear() {
        cart.clear();
    }

    /**
     * function to add book to cart
     * @param book book to add
     * @param quantity quantity of book to add
     */
    public void add(Book book, Integer quantity) {
        cart.put(book, quantity);
    }

    /**
     * function to remove book from cart by book
     * @param book book to remove
     */
    public void remove(Book book) {
        cart.remove(book);
    }

    /**
     * function to get the quantity of book in cart
     * @param book book to get quantity
     * @return quantity of book in cart
     */
    public int getQuantity(Book book) {
        return cart.get(book);
    }

    /**
     * function to add book to cart with no duplicate
     * @param book book to add
     */
    public void addBook(Book book) {
        //loop to check with book id if it is already in the cart
        for (Map.Entry<Book, Integer> entry : cart.entrySet()) {
            if (Objects.equals(entry.getKey().getId(), book.getId())) {
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        add(book, 1);
    }


    /**
     * function to remove book from cart by book id
     * @param id id of book to remove
     */
    public void removeBook(long id) {
        for (Map.Entry<Book, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getId() == id) {
                cart.remove(entry.getKey());
                return;
            }
        }
    }

    /**
     * function to get the total quantity of cart
     * @return total quantity of cart
     */
    public int getTotalQuantity() {
        int total = 0;
        for (Integer quantity : cart.values()) {
            total += quantity;
        }
        return total;
    }

    /**
     * function to get the total price of cart
     * @return total price of cart
     */
    public double getTotalPrice() {
        double total = 0;
        for (Book book : cart.keySet()) {
            total += book.getTotalPrice() * cart.get(book);
        }
        return total;
    }


    /**
     * function to update the quantity of book in cart
     * @param id id of book to update
     * @param quantity quantity of book to update
     */
    public void updateQuantity(long id, Integer quantity) {

        for (Map.Entry<Book, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getId() == id) {
                entry.setValue(quantity);
                return;
            }
        }
    }

    /**
     * function to get the original price of cart
     * @return original price of cart
     */
    public double getOriginalPrice() {
        double total = 0;
        for (Book book : cart.keySet()) {
            total += book.getPrice() * cart.get(book);
        }
        return total;
    }

    /**
     * function to get the discount price of cart
     * @return discount price of cart
     */
    public double getTotalDiscount() {
        double total = 0;
        for (Book book : cart.keySet()) {
            total += book.getSavePrice() * cart.get(book);
        }
        return total;
    }

    /**
     * function to get list of books cart
     * @return list of books cart
     */
    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : cart.keySet()) {
            books.add(book);
        }
        return books;
    }
}