package com.example.todolist.tsk.dto;

import com.example.todolist.tsk.model.Task;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDTO {

    public Long id;
    @JsonProperty("description")
	private String description;
	@JsonProperty("isCompleted")
	private Boolean isCompleted;
	
	
	public TaskDTO(Task task) {
		this.id = task.getId();
		this.description = task.getDescription();
		this.isCompleted = task.getIsCompleted();
	}
	
	public TaskDTO() {
		
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
	
	public static TaskDTO fromEntity(Task task) {
        return new TaskDTO(task);
    }

	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", description=" + description + ", isCompleted=" + isCompleted + "]";
	}
	
}
