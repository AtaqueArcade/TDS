package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import controlador.Controlador;
import java.awt.Color;
import java.awt.SystemColor;

public class GroupSettingsView {
	Popup popup;

	public GroupSettingsView() {
	}

	public void show() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			UnsupportedLookAndFeelException {
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		Map<String, Integer> contacts = Controlador.getInstance().getCurrentContacts();
		JTextField textFieldGroupName;
		JFrame frame = new JFrame("Group settings");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(SystemColor.textInactiveText);
		tabbedPane.addTab("New group", null, panel, "Create a new group chat");
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		panel_8.setBackground(SystemColor.textInactiveText);
		panel.add(panel_8, BorderLayout.NORTH);

		Component horizontalStrut_6 = Box.createHorizontalStrut(30);
		panel_8.add(horizontalStrut_6);

		JLabel lblContacts = new JLabel("Contact list\r\n");
		lblContacts.setForeground(Color.WHITE);
		lblContacts.setHorizontalAlignment(SwingConstants.LEFT);
		panel_8.add(lblContacts);

		Component horizontalStrut_7 = Box.createHorizontalStrut(45);
		panel_8.add(horizontalStrut_7);

		JLabel lblGroupName = new JLabel("Group name\r\n");
		lblGroupName.setForeground(Color.WHITE);
		lblGroupName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblGroupName);

		Component horizontalStrut_8 = Box.createHorizontalStrut(40);
		panel_8.add(horizontalStrut_8);

		JLabel lblContactsToBe = new JLabel("Contacts to be added\r\n");
		lblContactsToBe.setForeground(Color.WHITE);
		lblContactsToBe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_8.add(lblContactsToBe);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		panel_8.add(horizontalStrut_9);

		JList<String> list = new JList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		contacts.entrySet().stream().forEach(e -> {
			try {
				if (Controlador.getInstance().isContactoIndividual(e.getValue()))
					listModel.addElement(e.getKey());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		list.setModel(listModel);
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.textInactiveText);
		panel.add(panel_5, BorderLayout.WEST);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(100, 200));
		panel_5.add(scrollPane);

		JList<String> list_2 = new JList<String>();
		DefaultListModel<String> listModel_2 = new DefaultListModel<String>();
		list_2.setModel(listModel_2);
		JPanel scrollPane_2 = new JPanel();
		scrollPane_2.setBackground(SystemColor.textInactiveText);
		panel.add(scrollPane_2, BorderLayout.EAST);

		JScrollPane scrollPane_3 = new JScrollPane(list_2);
		scrollPane_3.setPreferredSize(new Dimension(100, 200));
		scrollPane_2.add(scrollPane_3);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		scrollPane_2.add(horizontalStrut_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textInactiveText);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel.add(panel_2, BorderLayout.CENTER);

		JButton btnNewButton_2 = new JButton("→");
		btnNewButton_2.setFont(font);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = list.getSelectedValuesList();
				for (String username : selections) {
					listModel.removeElement(username);
					listModel_2.addElement(username);
					list.setModel(listModel);
					list_2.setModel(listModel_2);
				}
			}
		});
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panel_2.add(verticalStrut_1);

		textFieldGroupName = new JTextField();
		textFieldGroupName.setToolTipText("Enter the group's name here");
		textFieldGroupName.setPreferredSize(new Dimension(100, 20));
		textFieldGroupName.setMinimumSize(new Dimension(100, 20));
		textFieldGroupName.setMaximumSize(new Dimension(100, 20));
		panel_2.add(textFieldGroupName);
		textFieldGroupName.setColumns(10);
		textFieldGroupName.setPreferredSize(new Dimension(100, 20));
		textFieldGroupName.setMinimumSize(new Dimension(100, 20));
		textFieldGroupName.setMaximumSize(new Dimension(100, 20));

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_3);
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("←");
		btnNewButton_3.setFont(font);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setOpaque(true);
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = list_2.getSelectedValuesList();
				for (String username : selections) {
					listModel_2.removeElement(username);
					listModel.addElement(username);
					list.setModel(listModel);
					list_2.setModel(listModel_2);
				}
			}
		});

		Component verticalStrut_4 = Box.createVerticalStrut(5);
		panel_2.add(verticalStrut_4);
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_2.add(btnNewButton_3);

		Component verticalStrut = Box.createVerticalStrut(57);
		panel_2.add(verticalStrut);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConfirm.setPreferredSize(new Dimension(100, 20));
		btnConfirm.setMinimumSize(new Dimension(100, 20));
		btnConfirm.setMaximumSize(new Dimension(100, 20));
		btnConfirm.setFont(font);
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setOpaque(true);
		btnConfirm.setBackground(SystemColor.textHighlight);
		panel_2.add(btnConfirm);

		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panel_2.add(verticalStrut_2);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(font);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setOpaque(true);
		btnCancel.setBackground(SystemColor.textHighlight);
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancel.setPreferredSize(new Dimension(100, 20));
		btnCancel.setMinimumSize(new Dimension(100, 20));
		btnCancel.setMaximumSize(new Dimension(100, 20));
		panel_2.add(btnCancel);
		btnCancel.addActionListener(e -> {
			frame.dispose();
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> contactList = IntStream.range(0, listModel_2.size()).mapToObj(listModel_2::get)
						.map(element -> (String) element).collect(Collectors.toList());
				if (!textFieldGroupName.getText().trim().equals(""))
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						Controlador.getInstance().addContact(textFieldGroupName.getText().trim(), contactList);
						JOptionPane.showMessageDialog(new JFrame(),
								"Group [" + textFieldGroupName.getText().trim() + "] created succesfully!\n",
								"New group", JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Please, enter a valid group name", "New group",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Group manager", null, panel2, "Edit or delete existing groups");
		panel2.setLayout(new BorderLayout(0, 0));

		JList<String> list2 = new JList<String>();
		DefaultListModel<String> listModel2 = new DefaultListModel<String>();
		LinkedList<Integer> idList = new LinkedList<Integer>();
		contacts.entrySet().stream().forEach(e -> {
			try {
				if (Controlador.getInstance().isGrupo(e.getValue())) {
					listModel2.addElement(e.getKey());
					idList.add(e.getValue());
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setModel(listModel2);
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.textInactiveText);
		panel2.add(panel_6, BorderLayout.WEST);

		Component horizontalStrut1 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut1);

		JScrollPane scrollPane1 = new JScrollPane(list2);
		scrollPane1.setPreferredSize(new Dimension(100, 200));

		panel_6.add(scrollPane1);

		JList<String> list3 = new JList<String>();
		JPanel panel1 = new JPanel();
		panel1.setBackground(SystemColor.textInactiveText);
		panel2.add(panel1, BorderLayout.EAST);
		JScrollPane scrollPane_ = new JScrollPane(list3);
		scrollPane_.setPreferredSize(new Dimension(100, 200));
		panel1.add(scrollPane_);
		Component horizontalStrut_ = Box.createHorizontalStrut(20);
		panel1.add(horizontalStrut_);
		JPanel panel4 = new JPanel();
		panel2.add(panel4, BorderLayout.CENTER);
		panel4.setLayout(new BorderLayout(0, 0));
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.textInactiveText);
		panel4.add(panel_7, BorderLayout.CENTER);
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		JList<String> contacts2 = new JList<String>();
		DefaultListModel<String> listModel3 = new DefaultListModel<String>();
		DefaultListModel<String> listModel_3 = new DefaultListModel<String>();
		list2.addListSelectionListener(e -> {
			try {
				List<String> contactList = Controlador.getInstance()
						.getGroupComponents(idList.get(list2.getSelectedIndex())).entrySet().stream().map(Entry::getKey)
						.collect(Collectors.toList());
				listModel3.clear();
				for (int i = 0; i < contactList.size(); i++) {
					listModel3.addElement(contactList.get(i));
				}
				list3.setModel(listModel3);
				listModel_3.clear();
				contacts.entrySet().stream().forEach(entry -> {
					try {
						if (Controlador.getInstance().isContactoIndividual(entry.getValue())
								&& !contactList.contains(entry.getKey()))
							listModel_3.addElement(entry.getKey());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				});
				list.setModel(listModel);
				contacts2.setModel(listModel_3);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		JScrollPane scrollPane_1 = new JScrollPane(contacts2);
		scrollPane_1.setPreferredSize(new Dimension(100, 70));
		panel_7.add(scrollPane_1);

		JButton button_1 = new JButton("←");
		button_1.setFont(font);
		button_1.setContentAreaFilled(false);
		button_1.setOpaque(true);
		button_1.setBackground(SystemColor.textHighlight);
		button_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = list3.getSelectedValuesList();
				for (String username : selections) {
					listModel_3.addElement(username);
					listModel3.removeElement(username);
					contacts2.setModel(listModel_3);
					list3.setModel(listModel3);
				}
			}
		});
		button_1.setAlignmentX(0.5f);
		panel_7.add(button_1);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setFont(font);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] selections = list2.getSelectedIndices();
				LinkedList<Integer> selectedIds = new LinkedList<Integer>();
				for (int s : selections)
					selectedIds.add(idList.get(s));
				try {
					if (Controlador.getInstance().deleteGroups(selectedIds)) {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						JOptionPane.showMessageDialog(new JFrame(), "Group deleted succesfully.", "Delete groups",
								JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					} else {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						JOptionPane.showMessageDialog(new JFrame(),
								"You don't have permision to delete the selected group.", "Delete groups",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (HeadlessException | InstantiationException | IllegalAccessException | ClassNotFoundException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton button = new JButton("→");
		button.setFont(font);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
		button.setBackground(SystemColor.textHighlight);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selections = contacts2.getSelectedValuesList();
				for (String username : selections) {
					listModel_3.removeElement(username);
					listModel3.addElement(username);
					contacts2.setModel(listModel_3);
					list3.setModel(listModel3);
				}
			}
		});
		button.setAlignmentX(0.5f);
		panel_7.add(button);

		PopupFactory pf = new PopupFactory();
		JPanel picPanel = new JPanel();
		picPanel.setBackground(SystemColor.controlDkShadow);
		picPanel.setPreferredSize(new Dimension(250, 50));
		picPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		picPanel.setLayout(new BoxLayout(picPanel, BoxLayout.Y_AXIS));
		Component verticalStrut4 = Box.createVerticalStrut(8);
		picPanel.add(verticalStrut4);
		JPanel panel_24 = new JPanel();
		panel_24.setBackground(SystemColor.controlDkShadow);
		picPanel.add(panel_24);
		JTextField textField = new JTextField();
		panel_24.add(textField);
		textField.setColumns(10);
		JButton btnSet = new JButton("Set picture");
		btnSet.setFont(font);
		btnSet.setContentAreaFilled(false);
		btnSet.setOpaque(true);
		btnSet.setBackground(SystemColor.textHighlight);
		panel_24.add(btnSet);
		JButton btnChangePicture = new JButton("Set picture");
		panel_7.add(btnChangePicture);
		btnChangePicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				popup = pf.getPopup(frame.getContentPane(), picPanel,
						(int) btnChangePicture.getLocationOnScreen().getX() - 80,
						(int) btnChangePicture.getLocationOnScreen().getY() - 50);
				popup.show();
				btnChangePicture.setEnabled(false);
			}
		});
		btnSet.addActionListener(e -> {
			if (!textField.getText().equals("")) {
				try {
					Controlador.getInstance().setGroupPicture(idList.get(list2.getSelectedIndex()),
							textField.getText().trim());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			popup.hide();
			btnChangePicture.setEnabled(true);
		});
		textField.addActionListener(e -> {
			btnSet.doClick();
		});
		Border blackline = BorderFactory.createLineBorder(Color.black);
		picPanel.setBorder(blackline);

		btnChangePicture.setFont(font);
		btnChangePicture.setContentAreaFilled(false);
		btnChangePicture.setOpaque(true);
		btnChangePicture.setBackground(SystemColor.textHighlight);
		btnChangePicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnChangePicture.setPreferredSize(new Dimension(100, 20));
		btnChangePicture.setMinimumSize(new Dimension(100, 20));
		btnChangePicture.setMaximumSize(new Dimension(100, 20));
		btnNewButton_1.setPreferredSize(new Dimension(100, 20));
		btnNewButton_1.setMinimumSize(new Dimension(100, 20));
		btnNewButton_1.setMaximumSize(new Dimension(100, 20));
		panel_7.add(btnNewButton_1);

		JButton button_2 = new JButton("Confirm");
		button_2.setFont(font);
		button_2.setContentAreaFilled(false);
		button_2.setOpaque(true);
		button_2.setBackground(SystemColor.textHighlight);
		button_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list2.getSelectedIndex() >= 0) {
					try {

						List<String> contactList = IntStream.range(0, listModel3.size()).mapToObj(listModel3::get)
								.map(element -> (String) element).collect(Collectors.toList());
						if (Controlador.getInstance().editGroup(idList.get(list2.getSelectedIndex()), contactList)) {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							JOptionPane.showMessageDialog(new JFrame(),
									"Group [" + list2.getSelectedValuesList().get(0) + "] edited succesfully!\n",
									"Edit group", JOptionPane.INFORMATION_MESSAGE);
						} else {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							JOptionPane.showMessageDialog(new JFrame(),
									"Group [" + list2.getSelectedValuesList().get(0) + "] couldn't be edited.\n",
									"Edit group", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (HeadlessException | InstantiationException | IllegalAccessException
							| ClassNotFoundException | UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
					frame.dispose();
				}
			}
		});
		button_2.setPreferredSize(new Dimension(100, 20));
		button_2.setMinimumSize(new Dimension(100, 20));
		button_2.setMaximumSize(new Dimension(100, 20));
		button_2.setAlignmentX(0.5f);
		panel_7.add(button_2);

		JButton button_3 = new JButton("Cancel");
		button_3.setFont(font);
		button_3.setContentAreaFilled(false);
		button_3.setOpaque(true);
		button_3.setBackground(SystemColor.textHighlight);
		button_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_3.addActionListener(e -> {
			frame.dispose();
		});
		button_3.setPreferredSize(new Dimension(100, 20));
		button_3.setMinimumSize(new Dimension(100, 20));
		button_3.setMaximumSize(new Dimension(100, 20));
		button_3.setAlignmentX(0.5f);
		panel_7.add(button_3);
		JPanel l3 = new JPanel();
		l3.setLayout(new BoxLayout(l3, BoxLayout.X_AXIS));
		l3.setBackground(SystemColor.textInactiveText);
		panel2.add(l3, BorderLayout.NORTH);

		Component horizontalStrut_2 = Box.createHorizontalStrut(25);
		l3.add(horizontalStrut_2);
		JLabel lblNewLabel = new JLabel("Select the group");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		l3.add(lblNewLabel);
		Component horizontalStrut_4 = Box.createHorizontalStrut(25);
		l3.add(horizontalStrut_4);
		JLabel lblNewLabel_1 = new JLabel("Your contacts");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		l3.add(lblNewLabel_1);
		Component horizontalStrut_5 = Box.createHorizontalStrut(25);
		l3.add(horizontalStrut_5);
		JLabel lblNewLabel_2 = new JLabel("Contacts inside group");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.add(lblNewLabel_2);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		l3.add(horizontalStrut_3);
		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
}
