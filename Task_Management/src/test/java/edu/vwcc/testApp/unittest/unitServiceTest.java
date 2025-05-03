package edu.vwcc.testApp.unittest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.Optional;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import edu.vwcc.app.model.Task;
import edu.vwcc.app.model.Status;
import edu.vwcc.app.model.Priority;
import edu.vwcc.app.repository.TaskRepository;
import edu.vwcc.app.service.TaskService;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class unitServiceTest {

	    @Mock
	    private TaskRepository taskRepository;

	    @InjectMocks
	     TaskService taskservice;

	    @Test
		@DisplayName("Test of creating a task")
	    void createTask() {
	        // Arrange
	    	LocalDate date =  LocalDate.now();
	        Task mockTask = new Task("T1", "D1",Status.PENDING, Priority.HIGH, date);
	        when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));

	        // Act
	       Task task =	taskRepository.findById(1L).get();

	        // Assert
	        assertNotNull(task);
	        assertEquals("T1", task.getTitle());
	        assertEquals("D1", task.getDiscription());
	        assertEquals(Status.PENDING, task.getStaus());
	        assertEquals(Priority.HIGH, task.getPriority());
	        assertEquals(date, task.getDuedate());
	        verify(taskRepository).findById(1L);
	    }




	@Test
	@DisplayName("Test of creating a task, then delete from the repo")
	void deleteTask() {
		// Arrange
		LocalDate date =  LocalDate.now();
		Task mockTask = new Task("T1", "D1",Status.PENDING, Priority.HIGH, date);
		when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));

		Task task =	taskRepository.findById(1L).get();

		//delete the task
		taskRepository.delete(task);


		verify(taskRepository, times(1)).delete(task);
	}



	@Test
	@DisplayName("Test of editing a task")
	void editTask() {
		// Arrange
		LocalDate date =  LocalDate.now();
		Task mockTask = new Task("T1", "D1",Status.PENDING, Priority.HIGH, date);
		when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));

		// Act
		Task task =	taskRepository.findById(1L).get();

		task.updateTask("T2", "D2",Status.COMPLETED, Priority.HIGH, date);

		// Assert
		assertNotNull(task);
		assertEquals("T2", task.getTitle());
		assertEquals("D2", task.getDiscription());
		assertEquals(Status.COMPLETED, task.getStaus());
		assertEquals(Priority.HIGH, task.getPriority());
		assertEquals(date, task.getDuedate());
		verify(taskRepository).findById(1L);
	}


}

