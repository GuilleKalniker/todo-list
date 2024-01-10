package com.example.todolist.tsk.controller;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.tsk.dto.TaskDTO;
import com.example.todolist.tsk.model.Task;
import com.example.todolist.tsk.repository.TaskRepository;

@RestController
@RequestMapping("/api")
public class TaskController {
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private  TaskRepository taskRepository;
	
    
    @GetMapping(value = "/tasks")
    public List<TaskDTO> getTasks() {
    	List<Task> tasks = taskRepository.findAll();
    	logger.info("GET to tasks finished");
    	
    	return tasks.stream().map(t -> TaskDTO.fromEntity(t)).collect(Collectors.toList());
    	
    }
    
    @GetMapping(value = "/tasks/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
    	
    	logger.info("Getting task: " + id);
    	Task task;
    	task = taskRepository.findById(id).orElseThrow(() -> new InvalidParameterException("There is no task with id: " + id));
    
        return TaskDTO.fromEntity(task);
    }
   
   @PostMapping(value = "/tasks")
   public TaskDTO createTask(@RequestBody TaskDTO dto) {
    	
	   logger.info("Creating task");
	   Task task = new Task();
	   populate(task,dto);
		
	   taskRepository.save(task);
	   logger.info("POST to task finished");
	   
	   return TaskDTO.fromEntity(task);
    }
    
   @PutMapping(value = "/tasks/{id}")
   public TaskDTO updateTaskById(@PathVariable Long id, @RequestBody TaskDTO dto) {
	   
	   logger.info("Updating task with id: " + id);
	   Task task = taskRepository.findById(id).orElseThrow(() -> new InvalidParameterException("There is no task with id: " + id));
	   populate(task, dto);
       taskRepository.save(task);
	   
       return TaskDTO.fromEntity(task);
    }
    
   @DeleteMapping(value = "/tasks/{id}")
   public String deleteTaskById(@PathVariable Long id) {
    	
	   logger.info("Deleting task with id: " + id);
		
	   taskRepository.deleteById(id);
	   return "Successfully deleted task with id: " + id;
    }
   
   
   private void populate(Task task, TaskDTO dto) {
		task.setDescription(dto.getDescription());
		task.setIsCompleted(dto.getIsCompleted());
	}
    
}
