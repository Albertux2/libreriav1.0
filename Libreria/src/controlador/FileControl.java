package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import modelo.Book;

public class FileControl {
	
	private static FileControl instance;
	
	private FileControl() {
		
	}
	
	public static FileControl getInstance() {
		if (instance == null) {
			instance = new FileControl();
		}
		return instance;
	}
	
	public void save(String path, Book objeto, boolean add) {
		try {
			File f = new File(path);
			ObjectOutputStream adaptadorW = null;
			if (add && f.exists() && f.length() > 0) {
				adaptadorW = new MyObjectOutputStream(new FileOutputStream(f, true));
			} else {
				adaptadorW = new ObjectOutputStream(new FileOutputStream(f, false));
			}
			adaptadorW.writeObject(objeto);
			adaptadorW.close();

		} catch (IOException e) {
		}
	}

	public Book getWithISBN(String path, String isbn) {
		File file = new File(path);
		Book libro = null;
		if (file.exists()) {
			try (FileInputStream flujoR = new FileInputStream(file);
					ObjectInputStream adaptadorR = new ObjectInputStream(flujoR);) {

				libro = (Book) adaptadorR.readObject();
				while (libro != null) {
					if (libro.getISBN().equals(isbn)) {
						return libro;
					}
					libro = (Book) adaptadorR.readObject();
				}
			} catch (Exception e) {
				return null;

			}
		}
		return null;
	}

	public Book getWithIndex(String path, int index) {
		File file = new File(path);
		Book book = null;
		int currentState = 0;
		if (file.exists()) {
			try (FileInputStream flujoR = new FileInputStream(file);
					ObjectInputStream adaptadorR = new ObjectInputStream(flujoR);) {

				book = (Book) adaptadorR.readObject();
				while (book != null) {
					if (index == currentState) {
						return book;
					}
					currentState++;
					book = (Book) adaptadorR.readObject();
				}
			} catch (Exception e) {
				return null;

			}
		}
		return null;
	}

	public ArrayList<Book> delete(String path, String bookToDelete) {
		File file = new File(path);
		Book book = null;
		ArrayList<Book> list = new ArrayList();
		if (file.exists()) {
			try (FileInputStream flujoR = new FileInputStream(file);
					ObjectInputStream adaptadorR = new ObjectInputStream(flujoR);) {
				book = (Book) adaptadorR.readObject();
				while (book != null) {
					String isbn = book.getISBN();
					if (!isbn.equals(bookToDelete)) {
						list.add(book);
					}
					book = (Book) adaptadorR.readObject();
				}
			} catch (Exception e) {
				return list;

			}
		}
		return list;
	}

	public void saveList(String path, ArrayList<Book> books) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		boolean first = true;
		for (Book book : books) {
			if (first) {
				this.save(path, book, false);
				first = false;
			} else {
				this.save(path, book, true);
			}
		}
	}
}
