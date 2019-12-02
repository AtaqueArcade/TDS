package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VistaLogin extends JPanel{

	private JTextField txtNombre;
	private JTextField txtPasswd;

	public VistaLogin(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		add(panel, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 50));
		panel.add(verticalStrut_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		add(panel_1, BorderLayout.SOUTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 50));
		panel_1.add(verticalStrut);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(Color.BLACK);
		panel_4.add(lblUser);
		
		txtNombre = new JTextField();
		txtNombre.setPreferredSize(new Dimension(10, 20));
		panel_4.add(txtNombre);
		txtNombre.setColumns(13);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GRAY);
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.GRAY);
		panel_5.add(panel_6, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.DARK_GRAY);
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setActionCommand("btnLogin");
		panel_6.add(btnLogin);
		btnLogin.addActionListener(e -> {
			String user = txtNombre.getText();
			String passwd = txtPasswd.getText();
				boolean login = Controlador.getUnicaInstancia().login(user,passwd);
				if (login){
					removeAll();
				}
				else {//Popup
				}	
				/*
				frmLogin.frame.setContentPane(new PagExplorar());
				frmLogin.frame.revalidate();
				frmLogin.frame.repaint();
				*/
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.GRAY);
		panel_5.add(panel_7, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Password");
		label.setForeground(Color.BLACK);
		panel_7.add(label);
		
		txtPasswd = new JTextField();
		txtPasswd.setColumns(10);
		panel_7.add(txtPasswd);
	}
}
