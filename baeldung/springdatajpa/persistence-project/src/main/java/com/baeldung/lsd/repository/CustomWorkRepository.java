package com.baeldung.lsd.repository;

import com.baeldung.lsd.model.Worker;

import java.util.List;

public interface CustomWorkRepository {

    List<Worker> search(String exp);
}
