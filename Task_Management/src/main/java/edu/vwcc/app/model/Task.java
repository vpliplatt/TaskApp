package edu.vwcc.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;



@Entity
@Table(name = "tasks")
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
   // @NotEmpty(message = "Title cannot be empty.")
	private String title;	
    
	private String discription;
	private LocalDate duedate;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	@Enumerated(EnumType.STRING)
	public Status status;


	public Task(){};
	
	public Task( String title, String discription ,Status status, Priority priority ,LocalDate duedate) {
		this.title = title;
		this.discription = discription;
		this.status = status;
		this.priority = priority;
		this.duedate = duedate;
	}
	
	
	public void updateTask( String title, String discription ,Status status, Priority priority ,LocalDate duedate) {
		this.title = title;
		this.discription = discription;
		this.status = status;
		this.priority = priority;
		this.duedate = duedate;
	}
	

	public Long getId() {
		return id;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTitle() {
		return title;
		
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public LocalDate getDuedate() {
		return duedate;
	}

	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Status getStaus() {
		return status;
	}

	public void setStaus(Status status) {
		this.status = status;
	}
	
    // Object methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

}
