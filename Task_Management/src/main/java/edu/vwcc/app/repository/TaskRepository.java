package edu.vwcc.app.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.vwcc.app.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{


	List<Task> findAllByOrderByCreatedAtAsc();
	
}
