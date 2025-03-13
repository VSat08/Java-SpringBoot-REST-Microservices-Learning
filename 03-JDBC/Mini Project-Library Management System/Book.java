package library_mangement_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Book {
	private Connection connection;

	public Book(Connection connection) {
		this.connection = connection;
	}

	public void addBook(String title, int authorId) {
		try {
			String query = "INSERT INTO BOOKS(title,author_id) VALUES(?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setInt(2, authorId);
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBook(int id, String newTitle, int newAuthorId) {
		try {
			String query = "UPDATE BOOKS SET title = ?, author_id = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newTitle);
			statement.setInt(2, newAuthorId);
			statement.setInt(3, id);
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(int id) {
		try {
			String query = "DELETE FROM BOOKS WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getAllBooks() {
		List<String> books = new ArrayList<>();
		try {
			String query = "SELECT * FROM BOOKS";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				int id = result.getInt(1);
				String title = result.getString(2);
				int authorId = result.getInt(3);
				books.add("Book Id: " + id + ", Title: " + title + ", Author Id: " + authorId);
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}
}
