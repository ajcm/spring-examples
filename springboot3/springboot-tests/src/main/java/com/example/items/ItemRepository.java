package com.example.items;

import com.example.items.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long>, CrudRepository<Item, Long> {
}
