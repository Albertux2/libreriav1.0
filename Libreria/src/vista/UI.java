package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;

public class UI extends JFrame {

	protected JPanel contentPane;
	protected JTextField txtIsbn;
	protected JTextField txtTitulo;
	protected JTextField txtAutor;
	protected JTextField txtEditorial;
	protected JTextField txtPrecio;
	protected JButton btnGuardar;
	protected JButton btnSalir;
	protected JScrollPane scrollTabla;
	protected JTable tablaLibros;
	protected JButton btnBorrar;
	protected JButton btnConsultar;
	private JPanel panelFormato;
	protected JRadioButton rdbtnCartone;
	protected JRadioButton rdbtnRustica;
	protected JRadioButton rdbtnGrapada;
	protected JRadioButton rdbtnEspiral;
	protected JPanel panelEstado;
	protected JRadioButton rdbtnNewRadioButton_4;
	protected JRadioButton rdbtnNewRadioButton_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	protected ButtonGroup bgEstado = new ButtonGroup();
	protected ButtonGroup bgFormato = new ButtonGroup();
	private JPanel panel_1;
	protected JButton btnEdit;
	protected JPanel panelLibro;
	private JLabel lblNewLabel_2;
	protected JSpinner spnStock;
	protected JTextField spnTxt;
	protected JButton btnStock;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UI() {
		Color radioColor = new Color(133, 213, 237);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1152, 647);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(58, 175, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new LineBorder(new Color(34,117,124), 3, false));
		panelSuperior.setBackground(new Color(22,88,93));
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("LIBRERIA DE ALBERTO\r\n");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
		panelSuperior.add(lblTitulo);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(58, 175, 185));
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnStock = new JButton("");
		btnStock.setPreferredSize(new Dimension(150, 45));
		btnStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnStock);
		
		btnEdit = new JButton("");
		btnEdit.setPreferredSize(new Dimension(150, 45));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnEdit);
		btnConsultar.setPreferredSize(new Dimension(150, 45));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnConsultar);
		
		btnBorrar = new JButton("");
		btnBorrar.setPreferredSize(new Dimension(150, 45));
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnBorrar);
		
		btnGuardar = new JButton("");
		btnGuardar.setPreferredSize(new Dimension(150, 45));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnGuardar);
		
		btnSalir = new JButton("");
		btnSalir.setPreferredSize(new Dimension(150, 45));
		btnSalir.setMinimumSize(new Dimension(120, 21));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnSalir);
		
		JTabbedPane panelCentral = new JTabbedPane(JTabbedPane.TOP);
		panelCentral.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		panelLibro = new ImagePanel();
		panelLibro.setBackground(new Color(103,211,225));
		panelCentral.addTab("LIBRO", null, panelLibro, null);
		GridBagLayout gbl_panelLibro = new GridBagLayout();
		gbl_panelLibro.columnWidths = new int[]{134, 440, 418, 0};
		gbl_panelLibro.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40, 40};
		gbl_panelLibro.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelLibro.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panelLibro.setLayout(gbl_panelLibro);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 0;
		panelLibro.add(lblIsbn, gbc_lblIsbn);
		
		txtIsbn = new NoPasteField();
		GridBagConstraints gbc_txtIsbn = new GridBagConstraints();
		gbc_txtIsbn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_txtIsbn.gridx = 1;
		gbc_txtIsbn.gridy = 0;
		panelLibro.add(txtIsbn, gbc_txtIsbn);
		txtIsbn.setColumns(10);
		
		JLabel lblTituloLibro = new JLabel("Titulo");
		lblTituloLibro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblTituloLibro = new GridBagConstraints();
		gbc_lblTituloLibro.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloLibro.gridx = 0;
		gbc_lblTituloLibro.gridy = 1;
		panelLibro.add(lblTituloLibro, gbc_lblTituloLibro);
		
		txtTitulo = new NoPasteField();
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 1;
		panelLibro.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutor.gridx = 0;
		gbc_lblAutor.gridy = 2;
		panelLibro.add(lblAutor, gbc_lblAutor);
		
		txtAutor = new NoPasteField();
		GridBagConstraints gbc_txtAutor = new GridBagConstraints();
		gbc_txtAutor.insets = new Insets(0, 0, 5, 5);
		gbc_txtAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAutor.gridx = 1;
		gbc_txtAutor.gridy = 2;
		panelLibro.add(txtAutor, gbc_txtAutor);
		txtAutor.setColumns(10);
		 		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblEditorial = new GridBagConstraints();
		gbc_lblEditorial.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditorial.gridx = 0;
		gbc_lblEditorial.gridy = 3;
		panelLibro.add(lblEditorial, gbc_lblEditorial);
		
		txtEditorial = new NoPasteField();
		GridBagConstraints gbc_txtEditorial = new GridBagConstraints();
		gbc_txtEditorial.insets = new Insets(0, 0, 5, 5);
		gbc_txtEditorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditorial.gridx = 1;
		gbc_txtEditorial.gridy = 3;
		panelLibro.add(txtEditorial, gbc_txtEditorial);
		txtEditorial.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 0;
		gbc_lblPrecio.gridy = 4;
		panelLibro.add(lblPrecio, gbc_lblPrecio);
		
		txtPrecio = new NoPasteField();
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrecio.gridx = 1;
		gbc_txtPrecio.gridy = 4;
		panelLibro.add(txtPrecio, gbc_txtPrecio);
		txtPrecio.setColumns(10);
		
		lblNewLabel = new JLabel("Formato:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		panelLibro.add(lblNewLabel, gbc_lblNewLabel);
		
		panelFormato = new JPanel();
		panelFormato.setBackground(radioColor);
		panelFormato.setBorder(new LineBorder(new Color(171,171,171), 2, true));
		GridBagConstraints gbc_panelFormato = new GridBagConstraints();
		gbc_panelFormato.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelFormato.insets = new Insets(0, 0, 5, 5);
		gbc_panelFormato.gridx = 1;
		gbc_panelFormato.gridy = 5;
		panelLibro.add(panelFormato, gbc_panelFormato);
		panelFormato.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		
		rdbtnCartone = new JRadioButton("Carton\u00E9");
		rdbtnCartone.setBackground(radioColor);
		panelFormato.add(rdbtnCartone);
		
		rdbtnRustica = new JRadioButton("R\u00FAstica");
		rdbtnRustica.setBackground(radioColor);
		panelFormato.add(rdbtnRustica);
		
		rdbtnGrapada = new JRadioButton("Grapada");
		rdbtnGrapada.setBackground(radioColor);
		panelFormato.add(rdbtnGrapada);
		
		rdbtnEspiral = new JRadioButton("Espiral");
		rdbtnEspiral.setBackground(radioColor);
		panelFormato.add(rdbtnEspiral);
		
		lblNewLabel_1 = new JLabel("Estado:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 6;
		panelLibro.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		panelEstado = new JPanel();
		panelEstado.setBorder(new LineBorder(new Color(171,171,171), 2, true));
		FlowLayout flowLayout = (FlowLayout) panelEstado.getLayout();
		flowLayout.setHgap(50);
		panelEstado.setBackground(new Color(133, 213, 237));
		GridBagConstraints gbc_panelEstado = new GridBagConstraints();
		gbc_panelEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelEstado.insets = new Insets(0, 0, 5, 5);
		gbc_panelEstado.gridx = 1;
		gbc_panelEstado.gridy = 6;
		panelLibro.add(panelEstado, gbc_panelEstado);
		
		rdbtnNewRadioButton_4 = new JRadioButton("Reedici\u00F3n");
		rdbtnNewRadioButton_4.setBackground(new Color(133, 213, 237));
		panelEstado.add(rdbtnNewRadioButton_4);
		
		rdbtnNewRadioButton_5 = new JRadioButton("Novedad");
		rdbtnNewRadioButton_5.setBackground(new Color(133, 213, 237));
		panelEstado.add(rdbtnNewRadioButton_5);
		
		JPanel panelEstanteria = new JPanel();
		panelCentral.addTab("ESTANTERIA", null, panelEstanteria, null);
		GridBagLayout gbl_panelEstanteria = new GridBagLayout();
		gbl_panelEstanteria.columnWidths = new int[]{0, 0};
		gbl_panelEstanteria.rowHeights = new int[]{0, 0};
		gbl_panelEstanteria.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelEstanteria.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelEstanteria.setLayout(gbl_panelEstanteria);
		
		scrollTabla = new JScrollPane();
		GridBagConstraints gbc_scrollTabla = new GridBagConstraints();
		gbc_scrollTabla.fill = GridBagConstraints.BOTH;
		gbc_scrollTabla.gridx = 0;
		gbc_scrollTabla.gridy = 0;
		panelEstanteria.add(scrollTabla, gbc_scrollTabla);
		
		tablaLibros = new JTable() {
			public boolean isCellEditable(int row, int column) {
					return false;
			};
		};
		DefaultCellEditor editor = (DefaultCellEditor) tablaLibros.getDefaultEditor(Object.class);
		editor.setClickCountToStart(1);
		scrollTabla.setViewportView(tablaLibros);
		addRadiosToGroup(panelFormato,bgFormato);
		addRadiosToGroup(panelEstado,bgEstado);
		
		lblNewLabel_2 = new JLabel("Stock:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 7;
		panelLibro.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(1,0,100,1);  
		spnStock = new JSpinner(new SpinnerNumberModel(1, 0, 999, 1));
		spnTxt = ((JSpinner.DefaultEditor) spnStock.getEditor()).getTextField();
		spnStock.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_spnStock = new GridBagConstraints();
		gbc_spnStock.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnStock.insets = new Insets(0, 0, 0, getWidth()/3);
		gbc_spnStock.gridx = 1;
		gbc_spnStock.gridy = 7;
		panelLibro.add(spnStock, gbc_spnStock);
		setSmallIcon(btnConsultar,"src/img/libr.png","Consultar libro guardado");
		setSmallIcon(btnBorrar,"src/img/trashCan.png","Borrar libro");
		setSmallIcon(btnGuardar,"src/img/saveIcon.png","Guardar libro");
		setSmallIcon(btnSalir,"src/img/exit.png","Salir de la aplicación");
		setSmallIcon(btnEdit,"src/img/edit.png","Editar un libro guardado");
		setSmallIcon(btnStock,"src/img/stock.png","Gestionar stock libro");
	}
	
	private void addRadiosToGroup(JPanel panel,ButtonGroup group) {
		for (Component component : panel.getComponents()) {
			group.add((JRadioButton)component);
		}
	}
	
	private void setSmallIcon(JButton component,String link,String description) {
		ImageIcon img = new ImageIcon(link);
		ImageIcon resizedImg = new ImageIcon(img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		component.setToolTipText(description);
		component.setIcon(resizedImg);
	}
	public Component getTableEditorComponent() {
		DefaultCellEditor editor = (DefaultCellEditor) tablaLibros.getDefaultEditor(Object.class);
		return editor.getComponent();
	}
}
