package example.cashcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    Logger LOG = LoggerFactory.getLogger(CashCardController.class);
    private final CashCardRepository cashCardRepository;


    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId, Principal principal) {
        Optional<CashCard> cashCardOptional = cashCardRepository.findByIdAndOwner(requestedId, principal.getName());

        return cashCardOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, Principal principal, UriComponentsBuilder ucb) {

        CashCard cashCardWithOwner = new CashCard(null, newCashCardRequest.amount(), principal.getName());
        CashCard savedCashCard = cashCardRepository.save(cashCardWithOwner);

        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();

        LOG.info("createCashCard::new card: {} ", savedCashCard.id());

        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> updateCashCard(@RequestBody CashCard updateCashCardRequest,
                                                @PathVariable Long requestedId, Principal principal) {

        Optional<CashCard> cashCardOptional = cashCardRepository.findByIdAndOwner(requestedId, principal.getName());
        if (cashCardOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CashCard updatedCashCard = new CashCard(requestedId, updateCashCardRequest.amount(), principal.getName());
        cashCardRepository.save(updatedCashCard);

        return ResponseEntity.noContent().build();
    }



    @GetMapping
    public ResponseEntity<List<CashCard>> findAll(Pageable pageable, Principal principal) {
        Page<CashCard> page = cashCardRepository.findByOwner(
                principal.getName(),
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))));

        return ResponseEntity.ok(page.getContent());
    }



}