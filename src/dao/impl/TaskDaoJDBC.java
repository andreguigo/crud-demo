package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DbConnection;
import connection.DbException;
import dao.TaskDao;
import model.Task;

public class TaskDaoJDBC implements TaskDao {

	private Connection conn;

	public TaskDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public Boolean create(Task task) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("INSERT INTO task(title, completed) VALUES (?, false)");

			pstmt.setString(1, task.getTitle());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0)
				return true;

			return false;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConnection.closeStatement(pstmt);
		}
	}

	public List<Task> read() {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement("SELECT * FROM task");

			rset = pstmt.executeQuery();

			List<Task> dataTask = new ArrayList<Task>();

			while (rset.next()) {
				Task task = new Task();

				task.setId(rset.getInt("id"));
				task.setTitle(rset.getString("title"));
				task.setCompleted(rset.getBoolean("completed"));

				dataTask.add(task);
			}
			return dataTask;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConnection.closeStatement(pstmt);
			DbConnection.closeResultSet(rset);
		}
	}

	public Boolean update(Task task) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("UPDATE task SET Completed = ? WHERE Id = ?");

			pstmt.setBoolean(1, task.isCompleted());
			pstmt.setInt(2, task.getId());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0)
				return true;

			return false;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConnection.closeStatement(pstmt);
		}
	}

	public Boolean delete(Integer id) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("DELETE FROM task WHERE Id = ?");

			pstmt.setInt(1, id);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0)
				return true;

			return false;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConnection.closeStatement(pstmt);
		}
	}
}
