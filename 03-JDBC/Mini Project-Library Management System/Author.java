package library_mangement_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Author {
	private Connection connection;

	public Author(Connection connection) {
		this.connection = connection;
	}

	public void addAuthor(String name) {
		try {
			String query = "INSERT INTO AUTHORS(name) VALUES(?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateAuthor(int id, String newName) {
		try {
			String query = "UPDATE Authors SET name = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newName);
			statement.setInt(2, id);
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAuthor(int id) {
		try {
			String query = "DELETE FROM Authors WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getAllAuthor() {
		List<String> authors = new ArrayList<>();
		try {
			String query = "SELECT * FROM AUTHORS";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				int authorId = result.getInt(1);
				String name = result.getString(2);
				authors.add("Author Id: " + authorId + ", Author Name: " + name);
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authors;
	}
}
