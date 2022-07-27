package hac.ex4.repo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Date;


/**
 * Payment class
 */
@Entity
public class Payment implements Serializable {

    /**
     * id of payment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * date of purchase
     */
    @CreationTimestamp
    private Date creationDate;

    /**
     * total price of purchase
     */
    @PositiveOrZero
    @NotNull(message = "Discount is mandatory")
    private Double purchaseAmount;

    /**
     * username of user who made purchase
     */
    @NotEmpty
    @NotNull
    private String userName;

    /**
     * default constructor
     */
    public Payment() {}

    /**
     * constructor
     * @param purchaseAmount total price of purchase
     * @param userName username of user who made purchase
     * (the date upon creation is automatically set)
     */
    public Payment(Double purchaseAmount, String userName) {
        this.purchaseAmount = purchaseAmount;
        this.userName = userName;
    }

    /**
     * get id of payment database
     * @return id of payment database
     */
    public Long getId() {
        return id;
    }

    /**
     * set id of payment database
     * @param id id of payment database
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get date of purchase
     * @return date of purchase
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * set date of purchase
     * @param creationDate date of purchase
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * get total price of purchase
     * @return total price of purchase
     */
    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    /**
     * set total price of purchase
     * @param purchaseAmount total price of purchase
     */
    public void setPurchaseAmount(Double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    /**
     * get username of user who made purchase
     * @return username of user who made purchase
     */
    public double getAmount() {
        return purchaseAmount;
    }

    /**
     * set amount of purchase
     * @param amount amount of purchase
     */
    public void setAmount(double amount) {
        this.purchaseAmount = amount;
    }

    /**
     * set username of user who made purchase
     * @param userName username of user who made purchase
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * get username of user who made purchase
     * @return username of user who made purchase
     */
    public String getUserName() {
        return userName;
    }

    /**
     * function to get string object of all information about payment
     * @return string object of all information about payment
     */
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", purchaseAmount=" + purchaseAmount +
                ", userName='" + userName + '\'' +
                '}';
    }
}
