package sage.springcoder.jamhuborderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sage.springcoder.jamhuborderservice.domain.Customer;

import java.util.List;
import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByCustomerNameLike(String customerName);
}
