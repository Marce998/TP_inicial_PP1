package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ventanaAltaTipoContacto extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaAltaTipoContacto INSTANCE;
	private JTextField txtNuevoTipo;
	private JButton btnAgregarNuevoTipo;
	
	public static ventanaAltaTipoContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaAltaTipoContacto(); 	
			return new ventanaAltaTipoContacto();
		}
		else
			return INSTANCE;
	}

	
	
	public ventanaAltaTipoContacto() {
		
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				txtNuevoTipo.setText(null);
			}
		});
		
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
		
		JLabel lblNewLabel = new JLabel("Agregue el nuevo tipo de contacto");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		txtNuevoTipo = new JTextField();
		txtNuevoTipo.setBounds(95, 56, 239, 20);
		panel.add(txtNuevoTipo);
		txtNuevoTipo.setColumns(10);
		
		btnAgregarNuevoTipo = new JButton("Agregar");
		btnAgregarNuevoTipo.setBounds(172, 87, 89, 23);
		panel.add(btnAgregarNuevoTipo);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}


	public JTextField getTxtNuevoTipo() {
		return txtNuevoTipo;
	}



	public JButton getBtnAgregarNuevoTipo() {
		return btnAgregarNuevoTipo;
	}


	public void cerrar() {
		this.txtNuevoTipo.setText(null);
		this.dispose();
	}

}