package com.example.springdatajdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController("itemController")
public class ItemController {


    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id){

        var optionalItem  = itemRepository.findById(id);

        return optionalItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/item")
    public ResponseEntity<Item> putItem(@RequestBody Item item){
//
//        var itemExists  = itemRepository.existsById(item.id());
//
//        if (itemExists){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Item already exists");
//        }

        var newItem = itemRepository.save(item);

        return ResponseEntity.ok(newItem);

         //return null;
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<Item> getDetails(@PathVariable Long id){


        try {
            var optionalItem = itemRepository.findById(id);

            return optionalItem.map(ResponseEntity::ok)
                    .orElseThrow();

        }catch (Exception ex){
           // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"something bad occured",ex);
            return new  ResponseEntity<Item>(new Item(1L,"Bad item"),HttpStatus.BAD_REQUEST);

        }


    }



}
