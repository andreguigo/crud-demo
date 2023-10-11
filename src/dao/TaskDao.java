package dao;

import java.util.List;

import model.Task;

public interface TaskDao {

	Boolean create(Task task);
	
	List<Task> read();
	
	Boolean update(Task task);
	
	Boolean delete(Integer id);
}
