package presentacion.vista;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import persistencia.datosDesplegables.mysql.Pais;

public class ventanaAltaProvincia extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaAltaProvincia INSTANCE;
	private JTextField txtNuevaProvincia;
	private JComboBox txtPais;
	private JButton btnAgregarNuevaProvincia;
	
	public static ventanaAltaProvincia getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaAltaProvincia(); 	
			return new ventanaAltaProvincia();
		}
		else
			return INSTANCE;
	}
	
	public ventanaAltaProvincia() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 201);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agregue la nueva provincia");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		txtNuevaProvincia = new JTextField();
		txtNuevaProvincia.setBounds(95, 118, 239, 20);
		panel.add(txtNuevaProvincia);
		txtNuevaProvincia.setColumns(10);
		
		txtPais = new JComboBox();
		txtPais.setBounds(95, 56, 239, 20);
		Pais paises = new Pais();
		DefaultComboBoxModel modeloPaises = new DefaultComboBoxModel(paises.mostrarPaises());
		txtPais.setModel(modeloPaises);
		panel.add(txtPais);
		
		btnAgregarNuevaProvincia= new JButton("Agregar");
		btnAgregarNuevaProvincia.setBounds(170, 167, 89, 23);
		panel.add(btnAgregarNuevaProvincia);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese la provincia:");
		lblNewLabel_1.setBounds(95, 93, 100, 14);
		panel.add(lblNewLabel_1);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}


	public JTextField getTxtNuevaProvincia() {
		return txtNuevaProvincia;
	}



	public JButton getBtnAgregarNuevaProvincia() {
		return btnAgregarNuevaProvincia;
	}
	
	public JComboBox getTxtPais() {
		return txtPais;
	}


	public void cerrar() {
		this.txtNuevaProvincia.setText(null);
		this.dispose();
	}
}