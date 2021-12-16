package controlador.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import modelo.Book;

public class DatabaseAccess {
	private Connection connection;

	public DatabaseAccess() {
		super();
		this.connection = Database.getInstance().getConnection();
	}

	public void insertBook(Book book) {
		try (PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO BOOK(isbn,title,author,editorial,price,format,state,stock,genre)VALUES(?,?,?,?,?,?,?,?,?)")) {
			statement.setString(1, book.getISBN());
			statement.setString(2, book.getTitle().toUpperCase());
			statement.setInt(3, this.getAuthorIdByName(book.getAuthor()));
			statement.setInt(4, this.getEditorialIdByName(book.getEditorial()));
			statement.setFloat(5, book.getPrice());
			statement.setInt(6, this.getFormatIdByName(book.getFormat()));
			statement.setInt(7, this.getStateIdByName(book.getState()));
			statement.setInt(8, book.getQuantity());
			statement.setInt(9, this.getGenreIdByName(book.getGenre()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBook(Book book) {
		try (PreparedStatement statement = connection.prepareStatement(
				"UPDATE BOOK SET isbn=?, title=?, author=?, editorial=?, price=?, format=?, state=?, stock=?, genre=? WHERE isbn=?")) {
			statement.setString(1, book.getISBN());
			statement.setString(2, book.getTitle());
			statement.setInt(3, this.getAuthorIdByName(book.getAuthor()));
			statement.setInt(4, this.getEditorialIdByName(book.getEditorial()));
			statement.setFloat(5, book.getPrice());
			statement.setInt(6, this.getFormatIdByName(book.getFormat()));
			statement.setInt(7, this.getStateIdByName(book.getState()));
			statement.setInt(8, book.getQuantity());
			statement.setInt(9, this.getGenreIdByName(book.getGenre()));
			statement.setString(10, book.getISBN());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStock(Book book) {
		try (PreparedStatement statement = connection.prepareStatement("UPDATE BOOK SET stock=? WHERE isbn=?")) {
			statement.setInt(1, book.getQuantity());
			statement.setString(2, book.getISBN());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Book> getBooks() {
		HashMap<String, Book> books = new HashMap<String, Book>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOK");
			while (resultSet.next()) {
				books.put(resultSet.getString(2),
						new Book(resultSet.getString(2), resultSet.getString(3), getAuthorById(resultSet.getInt(4)),
								getEditorialById(resultSet.getInt(5)), resultSet.getFloat(6),
								getFormatById(resultSet.getInt(7)), getStateById(resultSet.getInt(8)),
								resultSet.getInt(9), getGenreById(resultSet.getInt(10))));
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<String, Book> getBooksFrom(String value, String field, boolean foreignTable) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		try (Statement statement = connection.createStatement()) {
			if (!foreignTable) {
				String query ="SELECT * FROM BOOK WHERE " + field + " LIKE '%" + value + "%'";
				return getHashMapFromQuery(query);
			} else {
				ArrayList<Integer> ids = new ArrayList<Integer>();
				ResultSet resultSet = statement
						.executeQuery("SELECT id FROM " + field + " WHERE name LIKE '%" + value + "%'");
				while (resultSet.next()) {
					ids.add(resultSet.getInt(1));
				}
				return getBooksFromIdList(ids, field);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private HashMap<String, Book> getBooksFromIdList(List<Integer> ids, String field) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		ids.forEach((e) -> {
			try (Statement statement2 = connection.createStatement();) {
				ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM BOOK WHERE " + field + "= " + e);
				while (resultSet2.next()) {
					books.put(resultSet2.getString(2),
							new Book(resultSet2.getString(2), resultSet2.getString(3),
									getAuthorById(resultSet2.getInt(4)), getEditorialById(resultSet2.getInt(5)),
									resultSet2.getFloat(6), getFormatById(resultSet2.getInt(7)),
									getStateById(resultSet2.getInt(8)), resultSet2.getInt(9),
									getGenreById(resultSet2.getInt(10))));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		});
		return books;
	}

	public HashMap getBooksFromNumber(String value, String field,String comparator) {
		if (!value.isEmpty()) {
		String query = "SELECT * FROM BOOK WHERE "+field+comparator+value;
		return getHashMapFromQuery(query);
		}
		return getBooks();
	}

	public String getFormatById(int id) {
		try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM format WHERE id=?")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private HashMap<String,Book> getHashMapFromQuery(String query){
		HashMap<String, Book> books = new HashMap<String, Book>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				books.put(resultSet.getString(2),
						new Book(resultSet.getString(2), resultSet.getString(3), getAuthorById(resultSet.getInt(4)),
								getEditorialById(resultSet.getInt(5)), resultSet.getFloat(6),
								getFormatById(resultSet.getInt(7)), getStateById(resultSet.getInt(8)),
								resultSet.getInt(9), getGenreById(resultSet.getInt(10))));
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getEditorialById(int id) {
		try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM editorial WHERE id=?")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAuthorById(int id) {
		try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM author WHERE id=?")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getGenreById(int id) {
		try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM genre WHERE id=?")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getStateById(int id) {
		try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM state WHERE id=?")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] getGenres() {
		ArrayList<String> genres = new ArrayList<String>();

		try (Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT name FROM genre");
			while (resultSet.next()) {
				genres.add(resultSet.getString(1));
			}
			String[] genresArray = new String[genres.size()];
			for (int i = 0; i < genresArray.length; i++) {
				genresArray[i] = genres.get(i);
			}
			return genresArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean containsEditorial(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM editorial WHERE name=?");) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean containsAuthor(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM author WHERE name=?");) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean containsBook(String isbn) {
		try (PreparedStatement statement = connection.prepareStatement("SELECT isbn FROM book WHERE isbn=?");) {
			statement.setString(1, isbn);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void insertEditorial(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("INSERT INTO editorial(name) VALUES(?)");) {
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertAuthor(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("INSERT INTO author(name) VALUES(?)");) {
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getAuthorIdByName(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM author WHERE name=?");) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getEditorialIdByName(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM editorial WHERE name=?");) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getStateIdByName(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM state WHERE name=?");) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getFormatIdByName(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM format WHERE name=?");) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getGenreIdByName(String name) {
		name = name.toUpperCase();
		try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM genre WHERE name=?");) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void deleteBook(String isbn) {
		try (PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE isbn=?");) {
			statement.setString(1, isbn);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
