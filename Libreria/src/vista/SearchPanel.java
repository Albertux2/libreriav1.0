package vista;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modelo.Book;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class SearchPanel extends JPanel {
	protected JTable bookTable;
	private JComboBox comboBox;
	private JComboBox comboBoxComparator;
	private final Color background = new Color(208, 220, 244);
	private JTextField searchText;
	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		setBackground(background);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{224, 0};
		gridBagLayout.rowHeights = new int[]{67, 229, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{176, -46, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(300, 300));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{58, 0};
		gbl_panel_1.rowHeights = new int[]{13, 21, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Buscar por...");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollTabla = new JScrollPane();

		GridBagConstraints gbc_scrollTabla = new GridBagConstraints();
		gbc_scrollTabla.fill = GridBagConstraints.BOTH;
		gbc_scrollTabla.gridx = 0;
		gbc_scrollTabla.gridy = 1;
		add(scrollTabla, gbc_scrollTabla);
		bookTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		JTableHeader tableHeader = bookTable.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setResizingAllowed(false);
		scrollTabla.setViewportView(bookTable);
		scrollTabla.getViewport().setBackground(background);
		panel.setBackground(background);
		panel_1.setBackground(background);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(300, 32767));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setBackground(background);
		
		comboBox = new JComboBox(new String[] {"Autor","Editorial","Titulo","ISBN","Formato","Genero","Estado","Stock","Precio"});
		comboBox.setMaximumSize(new Dimension(300, 32767));
		panel_2.add(comboBox);
		
		comboBoxComparator = new JComboBox(new String[]{">","<","="});
		comboBoxComparator.setMaximumSize(new Dimension(300, 32767));
		panel_2.add(comboBoxComparator);
		
		searchText = new JTextField();
		searchText.setMaximumSize(new Dimension(150, 150));
		searchText.setColumns(10);
		GridBagConstraints gbc_searchText = new GridBagConstraints();
		gbc_searchText.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchText.gridx = 1;
		gbc_searchText.gridy = 0;
		panel.add(searchText, gbc_searchText);
		comboBoxComparator.setVisible(false);
		comboBox.addActionListener((e)->{
			if(comboBox.getSelectedItem().equals("Stock") || comboBox.getSelectedItem().equals("Precio")) {
				comboBoxComparator.setVisible(true);
			}else {
				comboBoxComparator.setVisible(false);
			}
		});
	}
	
	public void fillTable(HashMap bookstore) {
		String nombreColumna[] = { "ISBN", "Titulo", "Autor", "Editorial", "Precio","Formato","Estado","Cantidad","Genero" };
		String[][] filasTabla = new String[bookstore.size()][nombreColumna.length];
		ArrayList<Book> books = new ArrayList(bookstore.values());
		
		for (int i = 0; i < books.size(); i++) {
			filasTabla[i][0] = books.get(i).getISBN();
			filasTabla[i][1] = books.get(i).getTitle();
			filasTabla[i][2] = books.get(i).getAuthor();
			filasTabla[i][3] = books.get(i).getEditorial();
			String precio = String.valueOf(books.get(i).getPrice());
			filasTabla[i][4] = precio;
			filasTabla[i][5] = books.get(i).getFormat();
			filasTabla[i][6] = books.get(i).getState();
			filasTabla[i][7] = String.valueOf(books.get(i).getQuantity());
			filasTabla[i][8] = books.get(i).getGenre();
		}
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombreColumna);
		bookTable.setModel(tablaCompleta);
	}
	
	public JTextField getSearchText() {
		return searchText;
	}
	
	public JTable getTablaLibros() {
		return bookTable;
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	public String getSelectedColumn() {
		return comboBox.getSelectedItem().toString();
	}
	
	public String getSelectedComparator() {
		return comboBoxComparator.getSelectedItem().toString();
	}


	public String getText() {
		return searchText.getText();
	}
}
