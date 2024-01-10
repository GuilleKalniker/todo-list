package com.example.todolist.tsk.controller;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
    	
    	 try {
    		 logger.info("Getting task: " + id);
	    	 Task task;
	    	 task = taskRepository.findById(id).orElseThrow(() -> new InvalidParameterException("There is no task with id: " + id));
             return new ResponseEntity<>(TaskDTO.fromEntity(task), HttpStatus.OK);
         } catch (Exception e) {
        	 String errorMessage = "Couldn't get the requested task: " + e.getMessage();
             return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
         }
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
   public ResponseEntity<?> updateTaskById(@PathVariable Long id, @RequestBody TaskDTO dto) {
	   
  	 try {
  		 logger.info("Updating task with id: " + id);
  		 Task task = taskRepository.findById(id).orElseThrow(() -> new InvalidParameterException("There is no task with id: " + id));
  		 populate(task, dto);
         taskRepository.save(task);
         return new ResponseEntity<>(TaskDTO.fromEntity(task), HttpStatus.OK);
         
     } catch (Exception e) {
    	 String errorMessage = "Couldn't update task: " + e.getMessage();
         return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
     }
    }
    
   @DeleteMapping(value = "/tasks/{id}")
   public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
	   
	   try {
		   logger.info("Deleting task with id: " + id);
		   Task task = taskRepository.findById(id).orElseThrow(() -> new InvalidParameterException("There is no task with id: " + id));
		   
		   taskRepository.delete(task);
	       return new ResponseEntity<>( "Successfully deleted task with id: " + id, HttpStatus.OK);
	         
	     } catch (Exception e) {
	    	 String errorMessage = "Couldn't delete task: " + e.getMessage();
	         return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	     } 
    }
   
   
   private void populate(Task task, TaskDTO dto) {
		task.setDescription(dto.getDescription());
		task.setIsCompleted(dto.getIsCompleted());
	}
    
}
