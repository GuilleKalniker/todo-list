package com.example.todolist.tsk.endpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.todolist.tsk.controller.TaskController;
import com.example.todolist.tsk.dto.TaskDTO;
import com.example.todolist.tsk.repository.TaskRepository;

@DataJpaTest
@ComponentScan("com.example.todolist.tsk.controller")
public class TaskServiceTest {
	

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskController taskController;
	
	
	// Create task test 
	@Test
	public void createTaskOkTest() {
		
		TaskDTO task = new TaskDTO();
		task.setDescription("Make a to-do list");
		task.setIsCompleted(true);
		
		taskController.createTask(task);
		
		assertTrue(taskRepository.findAll().size() > 0);
	}
	
	// Get to task by Id test
	@Test
	public void getToTaskByIdOk() {
		
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setDescription("Make a to-do list");
		taskDTO.setIsCompleted(true);
		
		TaskDTO createdDTO = taskController.createTask(taskDTO);
		
		TaskDTO dto = taskController.getTaskById(createdDTO.getId());
		
		assertEquals("Make a to-do list", dto.getDescription());
		assertTrue(dto.getIsCompleted());
	}
	
	// Get to all tasks test
	@Test
	public void getToAllTasksOk() {
		
		TaskDTO taskDTO1 = new TaskDTO();
		taskDTO1.setDescription("Make a to-do list");
		taskDTO1.setIsCompleted(false);
		
		TaskDTO taskDTO2 = new TaskDTO();
		taskDTO2.setDescription("Make a second to-do list");
		taskDTO2.setIsCompleted(true);
		
		taskController.createTask(taskDTO1);
		taskController.createTask(taskDTO2);
		
		assertEquals(2, taskController.getTasks().size());
	}
	
	// Delete task by id test
	@Test
	public void deleteTaskByIdTest() {
		
		TaskDTO dto = new TaskDTO();
		dto.setDescription("Make a to-do list");
		dto.setIsCompleted(false);
		
		TaskDTO createdTaskDto = taskController.createTask(dto);
		taskController.deleteTaskById(createdTaskDto.getId());
		
		assertTrue(taskController.getTasks().isEmpty());
	}
	
	// Update task test
	@Test
	public void updateTaskOkTest() {
		
		TaskDTO dto = new TaskDTO();
		dto.setDescription("Make a to-do list");
		dto.setIsCompleted(false);
		
		TaskDTO createdTaskDto = taskController.createTask(dto);
		
		TaskDTO updatedDto = new TaskDTO();
		updatedDto.setDescription("Make a wish list");
		updatedDto.setIsCompleted(true);
		
		TaskDTO finalDto = taskController.updateTaskById(createdTaskDto.getId(),updatedDto);
		
		assertEquals("Make a wish list",finalDto.getDescription());
		assertTrue(finalDto.getIsCompleted());
	}

}
