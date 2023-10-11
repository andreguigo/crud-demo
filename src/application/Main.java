package application;

import dao.DaoFactory;
import dao.TaskDao;
import model.Task;

public class Main {

	public static void main(String[] args) {

		TaskDao taskDAO = DaoFactory.createTaskDao();

		// create
		Task newTask = new Task(null, "new task to do", false);
		taskDAO.create(newTask);

		// read
		for (Task tsk : taskDAO.read()) {
			System.out.println(tsk.toString());
		}

		// update
		Task task = new Task();
		task.setId(1);
		task.setCompleted(true);
		System.out.println(taskDAO.update(task));

		// delete
		System.out.println(taskDAO.delete(1));
	}
}
