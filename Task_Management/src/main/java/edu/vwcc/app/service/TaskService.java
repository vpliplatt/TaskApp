package edu.vwcc.app.service;



import edu.vwcc.app.model.Task;
import edu.vwcc.app.repository.TaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public List<Task> getAllTasks(){
	 return taskRepository.findAll();
	}


}
