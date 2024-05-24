package com.example.springdatajdbc;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public record Item(@NonNull @Id Long id, String description) {
}
