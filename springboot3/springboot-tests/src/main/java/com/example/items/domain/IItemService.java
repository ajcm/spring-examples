package com.example.items.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IItemService {

    Page<Item> getItems(Pageable pageable);

    Optional<Item> getItem(Long id);

    void deleteItem(Item item);


    void updateItem(Item item);


    Item addItem(Item item);
}
