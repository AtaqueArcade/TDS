package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class VistaRegistro {

	private JFrame frmRegister;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistro window = new VistaRegistro();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaRegistro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setTitle("Register");
		frmRegister.setBounds(100, 100, 450, 300);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		frmRegister.getContentPane().add(panel, BorderLayout.NORTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 40));
		panel.add(verticalStrut);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		frmRegister.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 40));
		panel_1.add(verticalStrut_1);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.GRAY);
		panel_1.add(panel_13, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		frmRegister.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		JLabel lblBecomeAnAppchat = new JLabel("Become an Appchat member. Connect with people.");
		lblBecomeAnAppchat.setForeground(Color.WHITE);
		panel_3.add(lblBecomeAnAppchat);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_2.add(panel_4, BorderLayout.CENTER);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setForeground(Color.GRAY);
		splitPane.setBorder(new LineBorder(Color.GRAY, 1, true));
		splitPane.setBackground(Color.GRAY);
		splitPane.setEnabled(false);
		panel_4.add(splitPane);
		
		JPanel panel_5 = new JPanel();
		splitPane.setLeftComponent(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.GRAY);
		panel_7.add(panel_15, BorderLayout.NORTH);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.BLACK);
		panel_15.add(lblPhone);
		
		textField_3 = new JTextField();
		panel_15.add(textField_3);
		textField_3.setColumns(12);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.GRAY);
		panel_5.add(panel_11, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("Name\r\n");
		lblNewLabel_4.setForeground(Color.BLACK);
		panel_11.add(lblNewLabel_4);
		
		textField = new JTextField();
		panel_11.add(textField);
		textField.setColumns(12);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.GRAY);
		panel_5.add(panel_12, BorderLayout.CENTER);
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setForeground(Color.BLACK);
		panel_12.add(lblBirthday);
		
		JDateChooser dateChooser = new JDateChooser();
		panel_12.add(dateChooser);
		
		JPanel panel_6 = new JPanel();
		splitPane.setRightComponent(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.GRAY);
		panel_6.add(panel_8, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("User");
		lblNewLabel_2.setForeground(Color.BLACK);
		panel_8.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		panel_8.add(textField_1);
		textField_1.setColumns(13);
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.GRAY);
		panel_9.add(panel_14, BorderLayout.NORTH);
		
		JButton btnSubmit = new JButton("Back");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setRequestFocusEnabled(false);
		btnSubmit.setRolloverEnabled(false);
		btnSubmit.setForeground(Color.DARK_GRAY);
		btnSubmit.setBackground(Color.LIGHT_GRAY);
		btnSubmit.setPreferredSize(new Dimension(80, 23));
		panel_14.add(btnSubmit);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setRolloverEnabled(false);
		button.setRequestFocusEnabled(false);
		button.setPreferredSize(new Dimension(80, 23));
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(Color.LIGHT_GRAY);
		panel_14.add(button);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.GRAY);
		panel_6.add(panel_10, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.BLACK);
		panel_10.add(lblNewLabel_1);
		
		textField_4 = new JTextField();
		panel_10.add(textField_4);
		textField_4.setColumns(10);
	}

}
