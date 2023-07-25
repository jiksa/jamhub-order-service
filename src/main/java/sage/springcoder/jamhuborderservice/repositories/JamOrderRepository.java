package sage.springcoder.jamhuborderservice.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import sage.springcoder.jamhuborderservice.domain.Customer;
import sage.springcoder.jamhuborderservice.domain.JamOrder;
import sage.springcoder.jamhuborderservice.web.model.OrderStatusEnum;

import java.util.List;
import java.util.UUID;
@Repository
public interface JamOrderRepository extends JpaRepository<JamOrder, UUID> {

    Page<JamOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<JamOrder> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    JamOrder findOneById(UUID id);
}
