package hac.ex4.services;

import hac.ex4.repo.Payment;
import hac.ex4.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * service for payment
 */
@Transactional
@Service
public class PaymentService {

    /**
     * payment repository
     */
    @Autowired
    private PaymentRepository repository;

    /**
     * function to add payment to database
     * @param payment payment to add
     */
    public void addPayment(Payment payment) {
        repository.save(payment);
    }

    /**
     * function to get all payments from database
     * @return list of payments
     */
    public List<Payment> getPaymets() {
        return repository.findAll();
    }

    /**
     * function to get all payments sorted by date from database
     * @return list of payments
     */
    public List<Payment> getPaymetsSortedByDate() {
        return repository.findAllByOrderByCreationDate();
    }
}
