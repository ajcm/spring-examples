package com.baeldung.lsd.repository;

import com.baeldung.lsd.model.Worker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomWorkRepositoryImpl implements CustomWorkRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Worker> search(String exp) {

        String searchQueryParam = "%" + String.join("%", exp.split(" ")) + "%";

        TypedQuery<Worker> query = entityManager
                .createQuery("select w from Worker w where firstName like ?1", Worker.class);

        query.setParameter(1, searchQueryParam);

        return query.getResultList();

    }
}
