package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GroupSettingsView {
	public GroupSettingsView(){}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		JTextField textFieldGroupName;
		JTextField textField;
		JFrame frame = new JFrame("Group settings");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("New group", null, panel, "Create a new group chat");
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnConfirm);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_1.add(btnCancel);

		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "ejemplo_1", "ejemplo_2" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.WEST);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut);

		JScrollPane scrollPane = new JScrollPane(list);
		panel_5.add(scrollPane);

		JList list_2 = new JList();
		list_2.setModel(new AbstractListModel() {
			String[] values = new String[] { "ejemplo_anadido" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JPanel scrollPane_2 = new JPanel();
		panel.add(scrollPane_2, BorderLayout.EAST);

		JScrollPane scrollPane_3 = new JScrollPane(list_2);
		scrollPane_2.add(scrollPane_3);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		scrollPane_2.add(horizontalStrut_1);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel.add(panel_2, BorderLayout.CENTER);

		JButton btnNewButton_2 = new JButton("-->");
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		Component verticalStrut = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut);

		textFieldGroupName = new JTextField();
		textFieldGroupName.setToolTipText("Enter the group's name here");
		panel_2.add(textFieldGroupName);
		textFieldGroupName.setColumns(10);
		textFieldGroupName.setPreferredSize(new Dimension(100, 20));
		textFieldGroupName.setMinimumSize(new Dimension(100, 20));
		textFieldGroupName.setMaximumSize(new Dimension(100, 20));
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("<--");
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_2.add(btnNewButton_3);

		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Group manager", null, panel2, "Edit or delete existing groups");
		panel2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel2.add(panel_3, BorderLayout.SOUTH);

		JButton btnConfirm2 = new JButton("Confirm");
		btnConfirm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_3.add(btnConfirm2);

		JButton btnCancel2 = new JButton("Cancel");
		btnCancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_3.add(btnCancel2);

		JList list2 = new JList();
		list2.setModel(new AbstractListModel() {
			String[] values = new String[] { "Grupo_1", "Grupo_2", "Grupo_3" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JPanel panel_6 = new JPanel();
		panel2.add(panel_6, BorderLayout.WEST);

		Component horizontalStrut1 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut1);

		JScrollPane scrollPane1 = new JScrollPane(list2);
		panel_6.add(scrollPane1);

		JList list3 = new JList();
		list3.setModel(new AbstractListModel() {
			String[] values = new String[] { "contacto del grupo", "1", "2", "3", "4" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JPanel panel1 = new JPanel();
		panel2.add(panel1, BorderLayout.EAST);
		JScrollPane scrollPane_ = new JScrollPane(list3);
		panel1.add(scrollPane_);
		Component horizontalStrut_ = Box.createHorizontalStrut(20);
		panel1.add(horizontalStrut_);
		JPanel panel4 = new JPanel();
		panel2.add(panel4, BorderLayout.CENTER);
		panel4.setLayout(new BorderLayout(0, 0));
		JPanel panel_4 = new JPanel();
		panel4.add(panel_4, BorderLayout.SOUTH);
		JButton btnAdd = new JButton("Add\r\n");
		panel_4.add(btnAdd);
		JButton btnNewButton_1 = new JButton("Remove\r\n");
		panel_4.add(btnNewButton_1);
		JPanel panel_7 = new JPanel();
		panel4.add(panel_7, BorderLayout.CENTER);
		JList contacts = new JList();
		contacts.setModel(new AbstractListModel() {
			String[] values = new String[] { "contacto 1", "" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_3);
		JScrollPane scrollPane_1 = new JScrollPane(contacts);
		panel_7.add(scrollPane_1);
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_2);
		textField = new JTextField();
		textField.setColumns(10);
		textField.setPreferredSize(new Dimension(100, 20));
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setMaximumSize(new Dimension(100, 20));
		panel4.add(textField, BorderLayout.NORTH);
		JPanel l3 = new JPanel();
		panel2.add(l3, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("Select the group");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		l3.add(lblNewLabel);
		Component horizontalStrut_4 = Box.createHorizontalStrut(40);
		l3.add(horizontalStrut_4);
		JLabel lblNewLabel_1 = new JLabel("Your contacts");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		l3.add(lblNewLabel_1);
		Component horizontalStrut_5 = Box.createHorizontalStrut(50);
		l3.add(horizontalStrut_5);
		JLabel lblNewLabel_2 = new JLabel("Contacts inside group");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.add(lblNewLabel_2);
		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
