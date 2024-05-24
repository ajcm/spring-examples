package com.baeldung.lsd.repository;

import com.baeldung.lsd.model.Task;
import com.baeldung.lsd.model.TaskStatus;
import com.baeldung.lsd.projection.ProjectTask;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    Specification<Task> isTaskInProgress = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), TaskStatus.IN_PROGRESS);

    static Specification<Task> isDueDateBetween(LocalDate fromDate, LocalDate toDate) {
        return (root, query, builder) -> builder.between(root.get("dueDate"), fromDate, toDate);
    }

    @Query("select count(*) from Task t where t.status = :status")
    int countByState(TaskStatus status);

    @Modifying
    @Query("delete Task t where t.status = :status")
    int deleteByState(TaskStatus status);

    @Query
    List<Task> findByStatusNamed(TaskStatus status);

    @Query
    int countUnassigned();

    @Query
    @Modifying(clearAutomatically = true)
    int deleteUnassigned();

    @Query
    List<Task> findTasksByProjectNative(@Param("projectId") Long project);

    @Query("select project.name as name from Project project, Task task where task.project = project")
    List<ProjectTask> findTasksAndProjects();

    List<Task> findFirst5ByOrderByDueDateDesc();

    List<Task> findAllByOrderByStatusDesc();

    List<Task> findFirst5ByOrderByStatusDesc();

    List<Task> findAllByOrderByDueDateDescStatusDesc();

    List<Task> findByNameContaining(String taskName, Sort sort);

    @Query("select t from Task t")
    Slice<Task> getAll(Pageable p);

    Slice<Task> findByNameLike(String name, Pageable pageable);
}
