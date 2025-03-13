package library_mangement_system;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		DBConnector connector = new DBConnector();
		Connection connection = connector.getConnection();
		if (connection != null) {
			System.out.println("Database connected");
		}

		Author author = new Author(connection);
		Book book = new Book(connection);

		Scanner scanner = new Scanner(System.in);

		int choice;

		do {
			System.out.println("\nMenu: Library Management System");

			System.out.println("1. Add Author");
			System.out.println("2. Update Author");
			System.out.println("3. Delete Author");
			System.out.println("4. See All Authors");

			System.out.println("5. Add Books");
			System.out.println("6. Update Books");
			System.out.println("7. Delete Books");
			System.out.println("8. See All Books");

			System.out.println("9. Exit");

			System.out.print("Enter you Choice:");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {

				System.out.print("Enter Author Name");
				String name = scanner.nextLine();
				author.addAuthor(name);
				System.out.println("Author Added Successfully!");
				break;
			}
			case 2: {

				System.out.print("Enter Author ID");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Author New Name");
				String name = scanner.nextLine();
				author.updateAuthor(id, name);
				System.out.println("Author Updated Successfully!");
				break;
			}
			case 3: {

				System.out.print("Enter Author ID to delete");
				int id = scanner.nextInt();
				author.deleteAuthor(id);
				System.out.println("Author Deleted Successfully!");
				break;
			}
			case 4: {

				System.out.print("List of All Authors");

				for (String name : author.getAllAuthor()) {
					System.out.println(name);
				}
				break;
			}

			case 5: {

				System.out.print("Enter Book Title: ");
				String title = scanner.nextLine();
				System.out.print("Enter Author ID: ");
				int id = scanner.nextInt();

				book.addBook(title, id);

				System.out.println("Author Added Successfully!");
				break;
			}
			case 6: {

				System.out.print("Enter Book ID");
				int bId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Book New Name");
				String name = scanner.nextLine();
				System.out.print("Enter Book New Auhtor ID");
				int authId = scanner.nextInt();

				book.updateBook(bId, name, authId);

				System.out.println("Author Updated Successfully!");
				break;
			}
			case 7: {

				System.out.print("Enter Book ID to delete");
				int id = scanner.nextInt();
				book.deleteBook(id);
				System.out.println("Book Deleted Successfully!");
				break;
			}
			case 8: {

				System.out.print("List of All books");

				for (String name : book.getAllBooks()) {
					System.out.println(name);
				}
				break;
			}
			case 9: {

				System.out.print("Exiting...\nThanks for visiting!!");

				break;
			}

			default:
				System.out.println("Invalid choice. Please try agin.");
			}

		} while (choice != 9);
		scanner.close();
		connector.closeConnection();
	}

}
