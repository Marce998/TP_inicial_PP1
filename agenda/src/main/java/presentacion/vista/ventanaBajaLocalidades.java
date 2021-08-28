package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ventanaBajaLocalidades extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaBajaLocalidades INSTANCE;
	private JButton btnBorrarPais;
	private JButton btnBorrarProvincia;
	private JButton btnBorrarLocalidad;
	
	public static ventanaBajaLocalidades getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaBajaLocalidades(); 	
			return new ventanaBajaLocalidades();
		}
		else
			return INSTANCE;
	}
	
	public ventanaBajaLocalidades() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 429, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el tipo de localidad a borrar");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		btnBorrarPais = new JButton("Borrar País");
		btnBorrarPais.setBounds(150, 43, 120, 23);
		panel.add(btnBorrarPais);
		
		btnBorrarProvincia= new JButton("Borrar Provincia");
		btnBorrarProvincia.setBounds(150, 77, 120, 23);
		panel.add(btnBorrarProvincia);
		
		btnBorrarLocalidad = new JButton("Borrar Localidad");
		btnBorrarLocalidad.setBounds(150, 111, 120, 23);
		panel.add(btnBorrarLocalidad);
				
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JButton getBtnBorrarPais() {
		return btnBorrarPais;
	}

	public JButton getBtnBorrarProvincia() {
		return btnBorrarProvincia;
	}

	public JButton getBtnBorrarLocalidad() {
		return btnBorrarLocalidad;
	}
	
	public void cerrar() {
		this.dispose();
	}
}