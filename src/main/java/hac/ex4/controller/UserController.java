package hac.ex4.controller;

import hac.ex4.beans.Cart;
import hac.ex4.listeners.SessionListenerCounter;
import hac.ex4.repo.Book;
import hac.ex4.repo.Payment;
import hac.ex4.services.BookService;
import hac.ex4.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * user controller
 */
@Controller
public class UserController {

    /**
     * book service
     */
    @Autowired
    private BookService bookService;

    /**
     * payment service
     */
    @Autowired
    private PaymentService paymentService;

    /**
     * cart session
     */
    @Resource(name = "sessionScopeBeanCart")
    private Cart cartSession;

    /**
     * cart session listener
     */
    @Resource(name = "sessionListenerWithMetrics")
    private ServletListenerRegistrationBean<SessionListenerCounter> metrics;

    /**
     * constant message for fail add cart
     */
    final static String FAIL_ADD_TO_CART = "We were unable to add the product to the cart";
    /**
     * constant message for fail delete product from cart
     */
    final static String FAIL_DELETE_FROM_CART = "We were unable to delete the product from the cart";
    /**
     * constant message for fail reset the shopping cart
     */
    final static String FAIL_TO_CLEAR_CART = "We were unable to empty the cart";

    /**
     * landing page for user
     * display navbar menu and top 5 discount books
     * @param model model
     * @param book for thymeleaf recognize book object
     * @return user page
     */
    @GetMapping("/")
    public String main(Book book, Model model) {
        /**
         * get the books and cart data and send it to the view
         */
        model.addAttribute("books", bookService.getBooksByDiscount());
        model.addAttribute("numOfBooksAtCart", cartSession.getTotalQuantity());
        model.addAttribute("header", "Top 5 books with the most affordable discount");
        model.addAttribute("msgIfEmpty", "No books found");

        return "user/index";
    }

    /**
     * page with all data books
     *
     * @param model  model
     * @return all books page
     */
    @GetMapping("/all-books")
    public String getAllBooks(Model model) {

        /**
         * get the books and cart data and send it to the view
         */
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("numOfBooksAtCart", cartSession.getTotalQuantity());
        model.addAttribute("header", "All books");
        model.addAttribute("msgIfEmpty", "No books found");

        return "user/index";
    }

    /**
     * page with all data books sorted by name
     * @param model the model
     * @return all books page sorted by name
     */
    @GetMapping("/sort-by-name")
    public String sortByName(Model model) {

        model.addAttribute("books", bookService.getBooksSortByName());
        model.addAttribute("numOfBooksAtCart", cartSession.getTotalQuantity());
        model.addAttribute("header", "Books sorted by name");
        model.addAttribute("msgIfEmpty", "No books found");

        return "user/index";
    }

    /**
     * search for books by name
     *
     * @param keyword the keyword to search for
     * @param model the model
     * @return the page with search result books
     */
    @PostMapping("/search")
    public String postFilterBooksByName(String keyword, Model model) {

        /**
         * get the books from search and cart data and send it to the view
         */
        model.addAttribute("books", bookService.getBooksByNameContaining(keyword));
        model.addAttribute("numOfBooksAtCart", cartSession.getTotalQuantity());
        model.addAttribute("header", "Search results for: " + keyword);
        model.addAttribute("msgIfEmpty", "No books found for: \"" + keyword + "\"");

        return "user/index";
    }

    /**
     * handle with "/search" to GET method
     * send to home page
     * @return home page
     */
    @GetMapping("/search")
    public String getFilterBooksByName() {
        return "redirect:/";
    }

    /**
     * shooping cart page
     * Displays the shopping cart in the list
     * The list contains the books with the price of each book and total price
     * You can finish a purchase and you can go back to the home page to continue buying
     * @param book book- In order for the thymeleaf to recognize the object
     * @param model model
     * @return shopping cart page
     */
    @GetMapping("/shopping-cart")
    public String shoppingCart(Book book, Model model) {

        model.addAttribute("numOfBooksAtCart", cartSession.getTotalQuantity());
        model.addAttribute("shoppingCart", cartSession);
        model.addAttribute("error", "");

        return "user/shoppingCart";
    }

    /**
     * request to add a book to the cart
     *
     * @param id the id of the book to add to the cart
     * @param model the model
     * @return the home page
     */
    @PostMapping("/add-to-cart")
    public String postAddToCart(long id, Model model) {
        /**
         * try to add the book to the cart
         * if it fails, send an error message to the view
         * if it succeeds, continue to the home page for shopping
         */
        try {
            Book book = bookService.getBook(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
            cartSession.addBook(book);
        } catch (Exception e) {
            model.addAttribute("errorMsg", FAIL_ADD_TO_CART);
            return "redirect:/error";
        }
        return "redirect:/";
    }

    /**
     * handle with GET method request to "/add-to-cart"
     * @return the home page
     */
    @GetMapping("/add-to-cart")
    public String getAddToCart() {
        return "redirect:/";
    }

    /**
     * request to delete a book from the cart
     *
     * @param id the id of the book to delete from the cart
     * @param model the model
     * @return the shopping cart page
     */
    @PostMapping("/delete-from-cart")
    public String postDeleteFromCart(long id, Model model) {
        try {
            cartSession.removeBook(id);
        } catch (Exception e) {
            model.addAttribute("errorMsg", FAIL_DELETE_FROM_CART);
            return "redirect:/error";
        }
        return "redirect:/shopping-cart";
    }

    /**
     * handle with GET method request to "/delete-from-cart"
     * @return the shopping cart page
     */
    @GetMapping("/delete-from-cart")
    public String getDeleteFromCart() {
        return "redirect:/";
    }

    /**
     * request to clear the cart
     * reset the shopping cart
     * @param model the model
     * @return the shopping cart page with empty cart
     */
    @PostMapping("/clear-cart")
    public String postClearCart(Model model) {
        /**
         * try to clear the cart
         * if it fails, move to error page
         * if it succeeds, return to shopping cart page with empty cart
         */
        try {
            cartSession.clear();
        } catch (Exception e) {
            model.addAttribute("errorMsg", FAIL_TO_CLEAR_CART);
            return "redirect:/error";
        }
        return "redirect:/shopping-cart";
    }

    /**
     * handle with GET method request to "/clear-cart"
     * @param model the model
     * @return the shopping cart page with empty cart
     */
    @GetMapping("/clear-cart")
    public String getClearCart(Model model) {
        return "redirect:/shopping-cart";
    }

    /**
     * request to finish the purchase
     * At the end of the purchase, try to make the purchase of all the products that are in the cart.
     * If one of the products was missing, we will return to the shopping cart page and show exactly
     * which product is missing in stock and how many units are left of it.
     * Also the purchase will not be made.
     * If the purchase is successful, go to the "Successful purchase" page with the exact price of
     * the purchase.
     * @param model the model
     * @param request the request http object to use session
     * @return if success - successful purchase page, if fail - shopping cart page with custom error
     */
    @PostMapping("/check-out")
    public String postCheckOut(Model model, HttpServletRequest request) {
        try {
            bookService.checkOut(cartSession.getCart());
            String username = "";
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails)
                username = ((UserDetails) principal).getUsername();
            else
                username = principal.toString();

            double totalPrice = cartSession.getTotalPrice();
            Payment payment = new Payment(totalPrice, username);
            paymentService.addPayment(payment);
            request.getSession().invalidate();
            model.addAttribute("totalPrice", totalPrice);

        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("numOfBooksAtCart", cartSession.getTotalQuantity());
            model.addAttribute("shoppingCart", cartSession);
            return "user/shoppingCart";
        }
        return "user/success";
    }

    /**
     * handle with GET method request to "/check-out"
     * @return shopping cart page
     */
    @GetMapping("/check-out")
    public String getCheckOut() {
        return "redirect:/";
    }

}
