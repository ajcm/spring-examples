package example.cashcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CashCardRepository extends CrudRepository<CashCard, Long>, PagingAndSortingRepository<CashCard, Long> {

    //  CashCard findByIdAndOwner(Long id, String owner);

    Optional<CashCard> findByIdAndOwner(Long id, String owner);

    Page<CashCard> findByOwner(String owner, Pageable amount);

    boolean existsByIdAndOwner(Long id, String owner);

}