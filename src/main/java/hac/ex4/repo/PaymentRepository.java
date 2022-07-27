package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * BookRepository class
 * default scope of this Bean is "singleton"
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    /**
     * query to get all payments from database sorted by date
     * @return list of payments sorted by date
     */
    List<Payment> findAllByOrderByCreationDate();
}
