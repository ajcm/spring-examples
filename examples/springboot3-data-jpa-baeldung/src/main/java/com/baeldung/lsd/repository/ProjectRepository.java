package com.baeldung.lsd.repository;

import com.baeldung.lsd.model.Project;
import com.baeldung.lsd.model.Task;
import com.baeldung.lsd.model.TaskStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByCodeEquals(String code);

    @Transactional(readOnly = true)
    Optional<Project> findByIdEquals(Long id);

    Iterable<Project> findByCodeContains(String expression);




    @Query("select p from Project p where p.code like %:codeexp% and p.description like %:descexp%")
    List<Project> findByCodeAndDescription(@Param("codeexp") String code, @Param("descexp") String description);

    @Transactional
    long deleteByCodeContains(String expression);

    @Query("select p.name from Project p where p.code like :code")
    Optional<String> findNameByCode(String code);

    @Query("select count(*) from Task t group by t.project")
    List<Long> countTasks();

    @Query(nativeQuery = true, value = "select * from projects limit 1")
    Optional<Project> findSingleProject();


    @Query("select p from Project p where name  = ?1 and description = ?2")
    List<Project> findWithNameAndDescriptionBind(String name, String description);

    @Query("from Project p where code IN :codes")
    List<Project> findProjectsByCode(List<String> codes);

    @Query("select p from Project p")
    List<Project> allProjects(Sort sort);

    @Query("select p from Project p")
    List<Project> allProjectsByPage(Pageable p);

}
