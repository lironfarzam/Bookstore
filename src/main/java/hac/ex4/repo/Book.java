package hac.ex4.repo;

import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Book class
 */
@Entity
public class Book implements Serializable {

    /**
     * id of book
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * title of book
     */
    @NotEmpty(message = "Name is mandatory")
    private String bookName;

    /**
     * source image string of book
     */
    private String image;

    /**
     * quantity of book at database
     * positive number and not null
     */
    @PositiveOrZero
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    /**
     * price of book
     * positive number and not null
     */
    @Positive
    @NotNull(message = "Price is mandatory")
    private Double price;

    /**
     * discount of book
     * positive number and not null
     * range 0-100
     */
    @Range(min = 0, max = 100)
    @PositiveOrZero(message = "must be positive or zero")
    @NotNull(message = "Discount is mandatory")
    private Double discount;


    /**
     * default constructor
     */
    public Book() {}

    /**
     * Constructor
     * @param bookName title of book
     * @param image source image string of book
     * @param quantity quantity of book at database
     * @param price price of book
     * @param discount discount of book
     */
    public Book(String bookName, String image, Integer quantity, Double price, Double discount) {

        this.bookName = bookName;
        this.image = (checkImageResponse(image)) ?
                image : "../images/imageNotAvailable.jpg";
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    /**
     * function to set book id
     * @param id id of book at database
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * function to get book id
     * @return id of book at database
     */
    public Long getId() {
        return id;
    }

    /**
     * function to set book name
     * @param bookName title of book
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * function to set book image
     * @param image source image string of book
     */
    public void setImage(String image) {
        /**
         * check if image URL is valid
         */
        this.image = (checkImageResponse(image)) ?
            image : "../images/imageNotAvailable.jpg";
    }

    /**
     * function to set book quantity
     *
     * @param quantity quantity of book at database
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * function to set book price
     *
     * @param price price of book
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * function to set book discount
     *
     * @param discount discount of book
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * function to get book name
     *
     * @return title of book
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * function to get book image source
     * @return String with source image
     */
    public String getImage() {
        return image;
    }

    /**
     * function to get book quantity
     * @return Integer with quantity of book
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * function to get book price
     * @return Double with price of book
     */
    public Double getPrice() {
        return price;
    }

    /**
     * function to get the sum of money we saved from discount
     * @return Double with sum of money we saved from discount
     */
    public Double getSavePrice() {
        //return double number with 2 numbers after decimal point
        return Math.round((price * discount / 100) * 100.0) / 100.0;
    }
    /**
     * function to get the total price of the book after discount
     * @return Double with  total price of the book after discount
     */
    public Double getTotalPrice() {
        //return double number with 2 numbers after decimal point
        return Math.round((price - getSavePrice()) * 100.0) / 100.0;
    }

    /**
     * function to get book discount
     * @return Double with discount of book
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * function to check if image URL is valid
     * @param image URL of image
     * @return true if image URL is valid, false otherwise
     */
    public boolean checkImageResponse(String image) {
        try {
            java.net.URL url = new java.net.URL(image);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * function to check if book is equal to another book
     * @param book book to compare with
     * @return true if books are equal, false otherwise
     */
    public boolean equals(Book book)
    {
        return this.id == book.getId();
    }

    /**
     * function to get all parameters of book as string
     * @return String with all parameters of book
     */
    @Override
    public String toString(){
        return "Book{" +
                "id=" + id +
                ", bookName=" + bookName +
                ", image=" + image +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
