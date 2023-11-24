package com.baeldung.lsd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


@NamedQuery(name = "Task.findByStatusNamed", query = "select t from Task t where  status = :status")
@NamedQuery(name = "Task.countUnassigned", query = "select count(*) from Task t  where assignee is null")
@NamedQuery(name = "Task.deleteUnassigned", query = "delete Task t where assignee is null ")

@NamedNativeQuery(name = "Task.findTasksByProjectNative",
        query = "select * from tasks where project_id = :projectId",
        resultClass = Task.class)


@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();


    private String name;
    private String description;

    private LocalDate dueDate;
    private TaskStatus status;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne
    private Worker assignee;

    public Task() {

    }

    public Task(String name, String description, LocalDate dueDate, Project project) {
        this(name, description, dueDate, project, TaskStatus.TODO);
    }

    public Task(String name, String description, LocalDate dueDate, Project project, TaskStatus taskStatus) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.project = project;
        this.status = taskStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (uuid == null) {
            return other.uuid == null;
        } else return uuid.equals(other.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", project=" + (project != null ? project.getName() : "") +
                ", assignee=" + assignee +
                '}';
    }

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
