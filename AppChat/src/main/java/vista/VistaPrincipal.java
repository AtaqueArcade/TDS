package vista;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

@SuppressWarnings("serial")
public class VistaPrincipal extends JPanel {
	private JTextField textoNombre;
	private JTextField textoApellidos;
	private JTextField textoEmail;
	private JTextField textoUsuario;
	private JTextField textoPassword;
	private JTextField textoPasswordRep;
	private JDateChooser electorFecha;
	private JLabel Texto_faltan_campos;

	public VistaPrincipal() {

		setSize(new Dimension(850, 550));
		setLayout(new BorderLayout(0, 0));
		setBounds(0, 0, 800, 600);
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel, BorderLayout.NORTH);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 50));
		panel.add(verticalStrut);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		add(panel_1, BorderLayout.WEST);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(80, 0));
		panel_1.add(horizontalStrut);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		add(panel_2, BorderLayout.SOUTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 50));
		panel_2.add(verticalStrut_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		add(panel_3, BorderLayout.EAST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(80, 0));
		panel_3.add(horizontalStrut_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 205), 5, true));
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.WEST);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(80, 0));
		panel_5.add(horizontalStrut_2);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.EAST);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(80, 0));
		panel_6.add(horizontalStrut_3);

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.NORTH);

		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_7.add(lblRegistro);

		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8, BorderLayout.SOUTH);

		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));

		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.NORTH);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_10.add(verticalStrut_2);

		JPanel panel_12 = new JPanel();
		panel_9.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new BorderLayout(0, 0));

		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Nombre(*):");
		panel_13.add(lblNewLabel);

		textoNombre = new JTextField();
		panel_13.add(textoNombre);
		textoNombre.setColumns(20);

		JPanel panel_15 = new JPanel();
		panel_12.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));

		JPanel panel_16 = new JPanel();
		panel_15.add(panel_16, BorderLayout.NORTH);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setPreferredSize(new Dimension(2, 0));
		panel_16.add(horizontalStrut_6);

		JLabel lblApellidos = new JLabel("Apellidos:");
		panel_16.add(lblApellidos);

		textoApellidos = new JTextField();
		panel_16.add(textoApellidos);
		textoApellidos.setColumns(20);

		JPanel panel_11 = new JPanel();
		panel_15.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));

		JPanel panel_14 = new JPanel();
		panel_11.add(panel_14, BorderLayout.NORTH);

		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento(*):");
		panel_14.add(lblFechaNacimiento);

		electorFecha = new JDateChooser();
		panel_14.add(electorFecha);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalStrut_7.setPreferredSize(new Dimension(196, 0));
		panel_14.add(horizontalStrut_7);

		JPanel panel_17 = new JPanel();
		panel_11.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));

		JPanel panel_18 = new JPanel();
		panel_17.add(panel_18, BorderLayout.NORTH);

		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalStrut_8.setPreferredSize(new Dimension(22, 0));
		panel_18.add(horizontalStrut_8);

		JLabel lblEmail = new JLabel("Email:");
		panel_18.add(lblEmail);

		textoEmail = new JTextField();
		panel_18.add(textoEmail);
		textoEmail.setColumns(20);

		JPanel panel_19 = new JPanel();
		panel_17.add(panel_19, BorderLayout.CENTER);
		panel_19.setLayout(new BorderLayout(0, 0));

		JPanel panel_20 = new JPanel();
		panel_19.add(panel_20, BorderLayout.NORTH);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalStrut_9.setPreferredSize(new Dimension(134, 0));
		panel_20.add(horizontalStrut_9);

		JCheckBox chckbxDeseoRecibirCorreos = new JCheckBox("Deseo recibir correos de alertas y notificaciones.");
		panel_20.add(chckbxDeseoRecibirCorreos);

		JPanel panel_21 = new JPanel();
		panel_19.add(panel_21, BorderLayout.CENTER);
		panel_21.setLayout(new BorderLayout(0, 0));

		JPanel panel_22 = new JPanel();
		panel_21.add(panel_22, BorderLayout.NORTH);

		JLabel lblUsuario = new JLabel("Usuario(*):");
		panel_22.add(lblUsuario);

		textoUsuario = new JTextField();
		panel_22.add(textoUsuario);
		textoUsuario.setColumns(20);

		JPanel panel_23 = new JPanel();
		panel_21.add(panel_23, BorderLayout.CENTER);
		panel_23.setLayout(new BorderLayout(0, 0));

		JPanel panel_24 = new JPanel();
		panel_23.add(panel_24, BorderLayout.NORTH);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a(*):");
		panel_24.add(lblContrasea);

		textoPassword = new JTextField();
		panel_24.add(textoPassword);
		textoPassword.setColumns(20);

		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalStrut_10.setPreferredSize(new Dimension(18, 0));
		panel_24.add(horizontalStrut_10);

		JPanel panel_25 = new JPanel();
		panel_23.add(panel_25, BorderLayout.CENTER);
		panel_25.setLayout(new BorderLayout(0, 0));

		JPanel panel_26 = new JPanel();
		panel_25.add(panel_26, BorderLayout.NORTH);

		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a(*):");
		panel_26.add(lblRepetirContrasea);

		textoPasswordRep = new JTextField();
		panel_26.add(textoPasswordRep);
		textoPasswordRep.setColumns(20);

		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalStrut_11.setPreferredSize(new Dimension(60, 0));
		panel_26.add(horizontalStrut_11);

		JPanel panel_27 = new JPanel();
		panel_25.add(panel_27, BorderLayout.CENTER);
		panel_27.setLayout(new BorderLayout(0, 0));

		JPanel panel_28 = new JPanel();
		panel_27.add(panel_28, BorderLayout.NORTH);

		JLabel lblLosCamposMarcados = new JLabel("Campos (*) obligatorios.");
		panel_28.add(lblLosCamposMarcados);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(37, 0));
		panel_28.add(horizontalStrut_4);

		JButton btnRegistrarse = new JButton("Registrarse");
		panel_28.add(btnRegistrarse);

		JPanel panel_29 = new JPanel();
		panel_27.add(panel_29, BorderLayout.CENTER);
		panel_29.setLayout(new BorderLayout(0, 0));

		JPanel panel_30 = new JPanel();
		panel_29.add(panel_30, BorderLayout.NORTH);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_30.add(verticalStrut_3);

		Texto_faltan_campos = new JLabel("NO SE HAN RELLENADO LOS CAMPOS OBLIGATORIOS");
		Texto_faltan_campos.setForeground(Color.RED);
		panel_30.add(Texto_faltan_campos);
		Texto_faltan_campos.setVisible(false);

		JPanel panel_31 = new JPanel();
		panel_29.add(panel_31, BorderLayout.CENTER);

		JButton btnPremium = new JButton("Premium");
		panel_31.add(btnPremium);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setPreferredSize(new Dimension(105, 0));
		panel_31.add(horizontalStrut_5);

		JButton btnCancelar = new JButton("Cancelar");
		panel_31.add(btnCancelar);
	}
}