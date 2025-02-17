package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import controlador.Controlador;
import java.awt.Color;
import pulsador.Luz;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactSettingsView {
	private JTextField nameChangeTextField;

	public ContactSettingsView() {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		List<String> userlist = Controlador.getInstance().getUsernamesByFilter("");
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		JFrame frame = new JFrame("Contact settings");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textInactiveText);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("New contact", null, panel, "Add new contacts to your contact list");
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textInactiveText);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel.add(panel_1, BorderLayout.WEST);

		Component verticalStrut_2_1 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_2_1);
		JTextField textField = new JTextField();

		textField.setPreferredSize(new Dimension(100, 20));
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setMaximumSize(new Dimension(100, 20));
		panel_1.add(textField);
		textField.setColumns(10);

		Component verticalStrut_9_1 = Box.createVerticalStrut(5);
		panel_1.add(verticalStrut_9_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(font);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setOpaque(true);
		btnSearch.setBackground(SystemColor.textHighlight);
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnSearch.setMinimumSize(new Dimension(100, 30));
		btnSearch.setMaximumSize(new Dimension(100, 30));
		btnSearch.setAlignmentX(0.5f);
		panel_1.add(btnSearch);

		Component horizontalStrut_3 = Box.createHorizontalStrut(120);
		panel_1.add(horizontalStrut_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textInactiveText);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel.add(panel_2, BorderLayout.EAST);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(font);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setOpaque(true);
		btnAdd.setBackground(SystemColor.textHighlight);
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.setPreferredSize(new Dimension(100, 30));
		btnAdd.setMinimumSize(new Dimension(100, 30));
		btnAdd.setMaximumSize(new Dimension(100, 30));
		panel_2.add(btnAdd);
		Component horizontalStrut_2 = Box.createHorizontalStrut(120);
		panel_2.add(horizontalStrut_2);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		JList<String> list = new JList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < userlist.size(); i++)
			listModel.addElement(userlist.get(i));
		list.setModel(listModel);
		btnSearch.addActionListener(e -> {
			try {
				listModel.clear();
				List<String> newlist = Controlador.getInstance().getUsernamesByFilter(textField.getText().trim());
				for (int i = 0; i < newlist.size(); i++)
					listModel.addElement(newlist.get(i));
				list.setModel(listModel);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		textField.addActionListener(e -> {
			btnSearch.doClick();
		});
		btnAdd.addActionListener(e -> {
			List<String> selections = list.getSelectedValuesList();
			boolean noContactIsRepeated;
			try {
				noContactIsRepeated = Controlador.getInstance().checkContactList(selections);
				if (noContactIsRepeated) {
					for (String username : selections) {
						Controlador.getInstance().addContact(null, username);
					}
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JOptionPane.showMessageDialog(new JFrame(),
							"Selected contacts [" + String.join(",", selections) + "] added succesfully!\n",
							"New contacts", JOptionPane.INFORMATION_MESSAGE);
				} else {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JOptionPane.showMessageDialog(new JFrame(),
							"Conflicting contacts selected: Contact already in your contact list detected.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				frame.dispose();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		scrollPane.setViewportView(list);

		Component verticalStrut_7 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_7, BorderLayout.NORTH);

		Component verticalStrut_8 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_8, BorderLayout.SOUTH);
		JPanel panel2 = new JPanel();
		panel2.setBackground(SystemColor.textInactiveText);
		tabbedPane.addTab("Contact manager", null, panel2, "Manage your contacts");
		panel2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.textInactiveText);
		panel2.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_2);

		nameChangeTextField = new JTextField();
		nameChangeTextField.setToolTipText("Enter a new Alias for the selected contact");
		nameChangeTextField.setPreferredSize(new Dimension(100, 20));
		nameChangeTextField.setMinimumSize(new Dimension(100, 20));
		nameChangeTextField.setMaximumSize(new Dimension(100, 20));
		nameChangeTextField.setColumns(10);
		panel_3.add(nameChangeTextField);

		Component verticalStrut_9 = Box.createVerticalStrut(5);
		panel_3.add(verticalStrut_9);

		JButton btnChangeName = new JButton("Set alias");
		btnChangeName.setFont(font);
		btnChangeName.setContentAreaFilled(false);
		btnChangeName.setOpaque(true);
		btnChangeName.setBackground(SystemColor.textHighlight);
		btnChangeName.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnChangeName.setPreferredSize(new Dimension(100, 30));
		btnChangeName.setMinimumSize(new Dimension(100, 30));
		btnChangeName.setMaximumSize(new Dimension(100, 30));
		panel_3.add(btnChangeName);

		Component verticalStrut_4_1 = Box.createVerticalStrut(10);
		panel_3.add(verticalStrut_4_1);
		JButton btnDelete = new JButton("Delete");
		panel_3.add(btnDelete);
		btnDelete.setFont(font);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setOpaque(true);
		btnDelete.setBackground(SystemColor.textHighlight);
		btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDelete.setPreferredSize(new Dimension(100, 30));
		btnDelete.setMinimumSize(new Dimension(100, 30));
		btnDelete.setMaximumSize(new Dimension(100, 30));

		Component verticalStrut_10 = Box.createVerticalStrut(5);
		panel_3.add(verticalStrut_10);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.textInactiveText);
		panel_3.add(panel_5);

		JLabel lblImport = new JLabel("Import .txt...");
		lblImport.setForeground(Color.WHITE);
		panel_5.add(lblImport);

		Luz luz = new Luz();
		luz.addEncendidoListener(e -> {
			Object[] formats = { "Iphone", "Android v.1", "Android v.2" };
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				int n = JOptionPane.showOptionDialog(null, "Select the file's format", "Import messages",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, formats, formats[0]);
				File selectedFile = jfc.getSelectedFile();
				try {
					Controlador.getInstance().importMessages(selectedFile.getAbsolutePath(), n);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		luz.setColor(SystemColor.textHighlight);
		panel_5.add(luz);

		Component horizontalStrut_1 = Box.createHorizontalStrut(120);
		panel_3.add(horizontalStrut_1);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.textInactiveText);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut);
		JLabel lblUsersName = new JLabel("Contact's Name:");
		lblUsersName.setForeground(SystemColor.text);
		lblUsersName.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(lblUsersName);
		panel2.add(panel_4, BorderLayout.EAST);

		JLabel label_1 = new JLabel("[User info]");
		label_1.setForeground(Color.WHITE);
		label_1.setAlignmentX(0.5f);
		label_1.setBackground(Color.DARK_GRAY);
		panel_4.add(label_1);

		Component verticalStrut_12 = Box.createVerticalStrut(5);
		panel_4.add(verticalStrut_12);

		JLabel lblmoreUserInfo = new JLabel("Contact's phone:");
		lblmoreUserInfo.setForeground(SystemColor.textHighlightText);
		lblmoreUserInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(lblmoreUserInfo);

		MultiLineLabel label_2 = new MultiLineLabel("[User info]");
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(SystemColor.textInactiveText);
		label_2.setAlignmentX(0.5f);
		panel_4.add(label_2);

		Component horizontalStrut = Box.createHorizontalStrut(120);
		panel_4.add(horizontalStrut);

		Component verticalStrut_long = Box.createVerticalStrut(250);
		panel_4.add(verticalStrut_long);

		Map<String, Integer> contacts = Controlador.getInstance().getCurrentContacts();
		JList<String> list_1 = new JList<String>();
		LinkedList<Integer> idList = new LinkedList<Integer>();
		DefaultListModel<String> listModel_1 = new DefaultListModel<String>();
		contacts.entrySet().stream().forEach(e -> {
			listModel_1.addElement(e.getKey());
			idList.add(e.getValue());
		});
		list_1.setModel(listModel_1);
		list_1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					int index = list_1.getSelectedIndex();
					if (index >= 0) {
						try {
							label_1.setText(Controlador.getInstance().getContactName(idList.get(index)));
							label_2.setText(Controlador.getInstance().getContactPhone(idList.get(index)));
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				DefaultListModel<String> listModel_1 = new DefaultListModel<String>();
				for (int i = 0; i < contacts.size(); i++)
					try {
						listModel_1.addElement(Controlador.getInstance().getContactName(idList.get(i)));
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				list_1.setModel(listModel_1);
			}
		});
		btnDelete.addActionListener(e -> {
			int[] arr = list_1.getSelectedIndices();
			LinkedList<Integer> result = new LinkedList<Integer>();
			for (int i : arr)
				result.add(idList.get(i));

			if (result.size() == 0) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JOptionPane.showMessageDialog(new JFrame(),
							"Please, select any amount of contacts to be deleted.\n", "Delete contacts",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					if (Controlador.getInstance().deleteContact(result)) {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						JOptionPane.showMessageDialog(new JFrame(), "Selected contacts deleted succesfully.\n",
								"Delete contacts", JOptionPane.INFORMATION_MESSAGE);
					}
					DefaultListModel<String> listM = new DefaultListModel<String>();
					for (int i = 0; i < contacts.size(); i++)
						listM.addElement(Controlador.getInstance().getContactName(idList.get(i)));
					list_1.setModel(listM);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});

		Component verticalStrut_5 = Box.createVerticalStrut(5);
		panel2.add(verticalStrut_5, BorderLayout.NORTH);
		panel2.add(scrollPane_1, BorderLayout.CENTER);

		Component verticalStrut_6 = Box.createVerticalStrut(10);
		panel2.add(verticalStrut_6, BorderLayout.SOUTH);
		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 300));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		for (int i = 0; i < 2; i++) {
			tabbedPane.setBackgroundAt(i, SystemColor.controlDkShadow);
		}

		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!nameChangeTextField.getText().isEmpty()) {
					int[] arr = list_1.getSelectedIndices();
					LinkedList<Integer> result = new LinkedList<Integer>();
					for (int i : arr)
						result.add(idList.get(i));

					if (result.size() != 1) {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							JOptionPane.showMessageDialog(new JFrame(), "Please, select one contact.\n",
									"Delete contacts", JOptionPane.ERROR_MESSAGE);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							if (Controlador.getInstance().changeContactName(result.getFirst(),
									nameChangeTextField.getText().trim()))
								JOptionPane.showMessageDialog(new JFrame(), "Name changed succesfully.\n",
										"Delete contacts", JOptionPane.OK_OPTION);
						} catch (HeadlessException | InstantiationException | IllegalAccessException
								| ClassNotFoundException | UnsupportedLookAndFeelException e1) {
							e1.printStackTrace();
						}
					}
					nameChangeTextField.setText("");
				}
			}
		});

		tabbedPane.setBorder(null);
		tabbedPane.setBackground(SystemColor.textInactiveText);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
}
