package hac.ex4.controller;

import hac.ex4.repo.Book;
import hac.ex4.repo.Payment;
import hac.ex4.services.BookService;
import hac.ex4.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * admin controller
 */
@Controller
public class AdminController {

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
     * constant for error messages fail to load payments table
     */
    final static String FAIL_TO_LOAD_PAYMENTS_TABLE = "We were unable to load the table";
    /**
     * constant for error messages fail to add book
     */
    final static String FAIL_ADD_BOOK = "We could not add the book";
    /**
     * constant for error messages fail to delete book
     */
    final static String FAIL_DELETE_BOOK = "We were unable to delete the book";
    /**
     * constant for error messages fail update book
     */
    final static String FAIL_UPDATE_BOOK = "We were unable to update the book";

    /**
     * landing for admin
     *
     * @param model model
     * @return admin page
     */
    @GetMapping("/admin")
    public String mainAdmin( Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "admin/index";
    }

    /**
     * add book form page
     * just return empty form to add book
     *
     * @param book For the thymeleaf engine to recognize the object
     * @return add book page
     */
    @GetMapping("/admin/showAddBookForm")
    public String showAddBookForm(Book book) {
        return "admin/addBook";
    }

    /**
     * add book to database
     * add book form page
     * here we add the book to the database after validating the input
     * if validate fails we return the same page with the error message
     * if validate success we add the book to the database and return the main admin page
     * @param book book to add
     * @param result binding result object
     * @param model the model
     * @return main admin page
     */
    @PostMapping("/admin/add-book")
    public String addBook(  Book book, BindingResult result, Model model) {
        /**
         * if validate fails we return the same page with the error message
         */
        if (result.hasErrors()) {
            return "admin/addBook";
        }
        /**
         * if validate success we add the book to the database and return the main admin page
         */
        try {
            /**
             * if we add the book successfully we return the main admin page
             */
            bookService.saveBook(book);
            model.addAttribute("books", bookService.getBooks());
            /**
             * if we fail to add the book to database we return error page
             */
        } catch (Exception e) {
            model.addAttribute("errorMsg", FAIL_ADD_BOOK);
            return "redirect:/error";
        }
        model.addAttribute("books", bookService.getBooks());
        return "redirect:/admin";
    }

    /**
     *to handle with GET method request to /admin/edit
     * @param id the book id to edit
     * @return edit book page
     */
    @GetMapping("/admin/edit")
    public String getEditBook(@RequestParam("id") long id) {
        return "redirect:/admin";
    }

    /**
     * edit book form page
     * return form page with filled with the book to edit
     *
     *
     * @param id the book id to edit
     * @param model model to add attributes
     * @return edit book page
     */
    @PostMapping("/admin/edit")
    public String postEditBook(@RequestParam("id") long id , Model model) {
        /**
         * get the book id and send the details of the book to the form page
         */
        System.out.println("id: " + id);
        Book book = bookService.getBook(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "admin/updateBook";
    }

    /**
     * update book at database
     * here we update the book to the database after validating the input
     * if validate fails we return the same page with the error message
     * if validate success we update the book to the database and return the main admin page
     *
     * @param id the book id to update
     * @param book the book to update
     * @param result the binding result
     * @param model the model
     * @return admin page
     */
    @PostMapping("/admin/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book, BindingResult result, Model model) {
        /**
         * check validation result
         */
        if (result.hasErrors()) {
            book.setId(id);
            return "admin/updateBook";
        }

        try {
            bookService.saveBook(book);

        } catch (Exception e) {
            model.addAttribute("errorMsg", FAIL_UPDATE_BOOK);
            return "redirect:/error";
        }
        /**
         * if we update the book successfully we return the main admin page
         */
        model.addAttribute("books", bookService.getBooks());
        return "redirect:/admin";
    }


    /**
     * delete book from database by id
     * @param id the book id to delete
     * @param model the model
     * @return the main admin page
     */
    @PostMapping("/admin/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        /**
         * find the book and try do delete
         * if failed, go to error page
         * if success, go to admin home page
         */
        try {
            Book book = bookService
                    .getBook(id)
                    .orElseThrow(
                            () -> new IllegalArgumentException("Invalid book Id:" + id)
                    );
            bookService.deleteBook(book);
            model.addAttribute("books", bookService.getBooks());
        } catch (Exception e) {
            model.addAttribute("errorMsg", FAIL_DELETE_BOOK);
            return "redirect:/error";
        }
        return "redirect:/admin";
    }

    /**
     * show all payments
     * page with table that contain all payments at the site
     *
     * @param model the model
     * @return the page with payments table
     */
    @GetMapping("/admin/payments")
    public String payments(Model model) {
        /**
         * try to get the cart data and send it to page attribute
         * if failed, go to error page
         * if success, go to payments page
         */
        try {
            List<Payment> result = paymentService.getPaymetsSortedByDate();

            double sum = 0;

            for (Payment payment : result) {
                sum += payment.getAmount();
            }
            model.addAttribute("payments",result);
            model.addAttribute("sumOfPayments",sum);
        } catch (Exception e) {
            model.addAttribute("errorMsg", FAIL_TO_LOAD_PAYMENTS_TABLE);
            return "redirect:/error";
        }
        return "admin/payments";
    }
}
