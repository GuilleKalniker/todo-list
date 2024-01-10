package com.example.todolist.tsk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TASK")
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    public Long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="IS_COMPLETED")
	private Boolean isCompleted;

	
	 Task(Long id, String description, Boolean isCompleted) {
		this.id = id;
		this.description = description;
		this.isCompleted = isCompleted;
	}

	 public Task() {
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
