package com.example.items;

import com.example.items.domain.IItemService;
import com.example.items.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("itemController")
public class ItemController {


    @Autowired
    private IItemService service;


    @GetMapping("/items")
    public Page<Item> getItems(Pageable pageable) {
        return service.getItems(pageable);

    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        var optionalItem = service.getItem(id);

        return optionalItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/item/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteItem(@RequestBody Item item) {
        service.deleteItem(item);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/item/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateItem(@RequestBody Item item) {
        service.updateItem(item);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/item")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Item> putItem(@RequestBody Item item) {
        var newItem = service.addItem(item);

        return ResponseEntity.ok(newItem);
    }


}
