package presentacion.vista;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import persistencia.datosDesplegables.mysql.Pais;

public class ventanaBajaPais extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaBajaPais INSTANCE;
	private JComboBox txtPais;
	private JButton btnBorrarPais;
	
	public static ventanaBajaPais getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaBajaPais(); 	
			return new ventanaBajaPais();
		}
		else
			return INSTANCE;
	}
	
	public ventanaBajaPais() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 137);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el país a borrar");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		txtPais = new JComboBox();
		Pais paises = new Pais();
		DefaultComboBoxModel modeloPaises = new DefaultComboBoxModel(paises.mostrarPaises());
		txtPais.setModel(modeloPaises);
		txtPais.setBounds(132, 54, 162, 22);
		panel.add(txtPais);
		
		btnBorrarPais= new JButton("Borrar");
		btnBorrarPais.setBounds(172, 103, 89, 23);
		panel.add(btnBorrarPais);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JComboBox getTxtPais() {
		return txtPais;
	}

	public JButton getBtnBorrarPais() {
		return btnBorrarPais;
	}

	public void cerrar() {
		this.dispose();
	}
}