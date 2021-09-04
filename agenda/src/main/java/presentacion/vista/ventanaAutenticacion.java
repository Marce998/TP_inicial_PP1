package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class ventanaAutenticacion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaAutenticacion INSTANCE;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private JTextField txtIP;
	private JTextField txtPuerto;
	private JButton btnEntrar;
	
	public static ventanaAutenticacion getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaAutenticacion(); 	
			return new ventanaAutenticacion();
		}
		else
			return INSTANCE;
	}
	
	public ventanaAutenticacion() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 284);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 456, 223);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Autenticaci\u00F3n de usuario");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 436, 27);
		panel.add(lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblUsuario.setBounds(29, 65, 71, 14);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(121, 62, 86, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblContrasea.setBounds(29, 107, 71, 14);
		panel.add(lblContrasea);
		
		txtContraseña = new JTextField();
		txtContraseña.setBounds(121, 105, 86, 20);
		panel.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		JLabel lblP = new JLabel("Direcci\u00F3n IP");
		lblP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblP.setBounds(29, 150, 77, 14);
		panel.add(lblP);
		
		txtIP = new JTextField();
		txtIP.setHorizontalAlignment(SwingConstants.CENTER);
		txtIP.setText("localhost");
		txtIP.setBounds(121, 148, 86, 20);
		panel.add(txtIP);
		txtIP.setColumns(10);
		txtIP.setEditable(false);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPuerto.setBounds(245, 150, 55, 14);
		panel.add(lblPuerto);
		
		txtPuerto = new JTextField();
		txtPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuerto.setText("3308");
		txtPuerto.setBounds(310, 148, 86, 20);
		panel.add(txtPuerto);
		txtPuerto.setColumns(10);
		txtPuerto.setEditable(false);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(183, 189, 89, 23);
		panel.add(btnEntrar);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}


	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtContraseña() {
		return txtContraseña;
	}
	

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public void setTxtContraseña(JTextField txtContraseña) {
		this.txtContraseña = txtContraseña;
	}

	public JTextField getTxtIP() {
		return txtIP;
	}

	public JTextField getTxtPuerto() {
		return txtPuerto;
	}
	
	public JButton getBtnEntrar() {
		return btnEntrar;
	}

	public void cerrar() {
		this.dispose();
	}
}