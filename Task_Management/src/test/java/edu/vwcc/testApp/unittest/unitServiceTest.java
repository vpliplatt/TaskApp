package edu.vwcc.testApp.unittest;

import static org.junit.jupiter.api.Assertions.assertNotNull ;
import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.Optional;


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

@ExtendWith(MockitoExtension.class)
public class unitServiceTest {

	    @Mock
	    private TaskRepository taskRepository;

	    @InjectMocks
	     TaskService taskservice;

	    @Test
	    void TaskById() {
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
	    
	    
}

