package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ventanaAltaPais extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaAltaPais INSTANCE;
	private JTextField txtNuevoPais;
	private JButton btnAgregarNuevoPais;
	
	public static ventanaAltaPais getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaAltaPais(); 	
			return new ventanaAltaPais();
		}
		else
			return INSTANCE;
	}
	
	public ventanaAltaPais() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 121);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agregue el nuevo Pais");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		txtNuevoPais = new JTextField();
		txtNuevoPais.setBounds(95, 56, 239, 20);
		panel.add(txtNuevoPais);
		txtNuevoPais.setColumns(10);
		
		btnAgregarNuevoPais= new JButton("Agregar");
		btnAgregarNuevoPais.setBounds(172, 87, 89, 23);
		panel.add(btnAgregarNuevoPais);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}


	public JTextField getTxtNuevoPais() {
		return txtNuevoPais;
	}



	public JButton getBtnAgregarNuevoPais() {
		return btnAgregarNuevoPais;
	}


	public void cerrar() {
		this.txtNuevoPais.setText(null);
		this.dispose();
	}

}