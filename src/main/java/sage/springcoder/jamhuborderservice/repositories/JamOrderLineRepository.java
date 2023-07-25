package sage.springcoder.jamhuborderservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sage.springcoder.jamhuborderservice.domain.JamOrderLine;

import java.util.UUID;

@Repository
public interface JamOrderLineRepository extends PagingAndSortingRepository<JamOrderLine, UUID> {
}
