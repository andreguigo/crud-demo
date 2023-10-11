package dao;

import connection.DbConnection;
import dao.impl.TaskDaoJDBC;

public class DaoFactory {

	public static TaskDao createTaskDao() {
		return new TaskDaoJDBC(DbConnection.getConnection());
	}
}
