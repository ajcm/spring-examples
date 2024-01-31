package com.baeldung.lsd.repository;

import com.baeldung.lsd.model.Worker;
import com.baeldung.lsd.projection.WorkerFullname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long>, JpaSpecificationExecutor<Worker>, CustomWorkRepository {

    List<WorkerFullname> findByFirstName(String firstName);


}
