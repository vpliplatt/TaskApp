package edu.vwcc.app.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import edu.vwcc.app.model.Priority;
import edu.vwcc.app.model.Status;
import edu.vwcc.app.model.Task;
import edu.vwcc.app.repository.TaskRepository;
import edu.vwcc.app.service.TaskService;
import java.time.LocalDate;

@Controller
public class MainController {
	 @Autowired
	 private TaskRepository taskRepository;
	 private TaskService taskservice;

	   @GetMapping("/")
	    public String taskList(Model model) {
	        model.addAttribute("tasks",taskRepository.findAllByOrderByCreatedAtAsc());
	        return "index.html";
	        
	    }
	 
	   
	   
	    @GetMapping("/edit/{id}")
	    public String taskDetails(@PathVariable Long id, Model model) {
	        Optional<Task> taskOptional = taskRepository.findById(id);   
	        if (taskOptional.isEmpty()) {
	            // Return 404 status and let Spring Boot handle showing the error page
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id);
	        }  
		
	        model.addAttribute("task",taskOptional.get());
	        return "edittask.html";
	    }

	    
	   @PostMapping("/edit/{id}")
	    public String taskUpdate(@PathVariable("id") String id
	    			,@RequestParam String title
					,@RequestParam String discription
					,@RequestParam String priority
					,@RequestParam String status
					,@RequestParam String duedate
				//	,BindingResult result
				   ,Model model
				) {
		   long longID = Long.parseLong(id);
		   Task x = taskRepository.findById(longID).get();
		   
		   LocalDate date = LocalDate.parse(duedate);
		
			Status s = Status.valueOf(status);
			Priority p = Priority.valueOf(priority);

			x.updateTask(title, discription ,s ,p ,date);
	
			taskRepository.save(x);
	
			var tasks = taskRepository.findAllByOrderByCreatedAtAsc();
			System.out.println(tasks);
			
			model.addAttribute("tasks",tasks);
			return "redirect:/";
		

}
	    
	    
	   
		//Navigate to task page
		@PostMapping("/task")
		public String goToaskpage(){
			return "task.html";
		}

		
	    @GetMapping("/delete/{id}")
	    public String deleteTask(@PathVariable String id, Model model) {
	    		    	 	
	    	  System.out.println(id);
			  long longID = Long.parseLong(id);
			  Task t=  taskRepository.findById(longID).get();
			  taskRepository.delete(t);   	
			  var tasks = taskRepository.findAllByOrderByCreatedAtAsc();
			
			model.addAttribute("tasks",tasks);
			 return "redirect:/";
		 }
		
		
		
		@PostMapping("/add")
		public String addTask(@RequestParam String title
							,@RequestParam String discription
							,@RequestParam String priority
							,@RequestParam String status
							,@RequestParam String duedate
						//	,BindingResult result
							,Model model) {

			
			LocalDate date;
			if(duedate.isEmpty()) {
				date =  LocalDate.now();
			}
			else {
				date = LocalDate.parse(duedate);
			}
			
			Status s = Status.valueOf(status);
			Priority p = Priority.valueOf(priority);	
	
			Task t = new Task(title, discription ,s ,p ,date);
			
			taskRepository.save(t);
	
			var tasks = taskRepository.findAllByOrderByCreatedAtAsc();
			
			model.addAttribute("tasks",tasks);
			return "redirect:/";
	 
}
}