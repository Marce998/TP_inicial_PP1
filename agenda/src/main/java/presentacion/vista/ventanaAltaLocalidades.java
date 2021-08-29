package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ventanaAltaLocalidades extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ventanaAltaLocalidades INSTANCE;
	private JButton btnAgregarNuevoPais;
	private JButton btnAgregarNuevaProvincia;
	private JButton btnAgregarNuevaLocalidad;
	
	public static ventanaAltaLocalidades getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ventanaAltaLocalidades(); 	
			return new ventanaAltaLocalidades();
		}
		else
			return INSTANCE;
	}
	
	public ventanaAltaLocalidades() {
		
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
		
		JLabel lblNewLabel = new JLabel("Seleccione el tipo de localidad a agregar");
		lblNewLabel.setBounds(0, 11, 429, 21);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		btnAgregarNuevoPais = new JButton("Agregar País");
		btnAgregarNuevoPais.setBounds(150, 43, 120, 23);
		panel.add(btnAgregarNuevoPais);
		
		btnAgregarNuevaProvincia= new JButton("Agregar Provincia");
		btnAgregarNuevaProvincia.setBounds(150, 77, 120, 23);
		panel.add(btnAgregarNuevaProvincia);
		
		btnAgregarNuevaLocalidad = new JButton("Agregar Localidad");
		btnAgregarNuevaLocalidad.setBounds(150, 111, 120, 23);
		panel.add(btnAgregarNuevaLocalidad);
				
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}



	public JButton getBtnAgregarNuevoPais() {
		return btnAgregarNuevoPais;
	}



	public JButton getBtnAgregarNuevaProvincia() {
		return btnAgregarNuevaProvincia;
	}



	public JButton getBtnAgregarNuevaLocalidad() {
		return btnAgregarNuevaLocalidad;
	}
	
	public void cerrar() {
		this.dispose();
	}
}