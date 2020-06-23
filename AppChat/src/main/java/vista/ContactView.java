package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import controlador.Controlador;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.awt.SystemColor;

public class ContactView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int DELAY = 1000;
	private Map<String, ImageIcon> imageMap;
	private DefaultListModel<String> listModel;
	private JList<String> list;

	public ContactView() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		super(new BorderLayout());
		Map<String, Integer> contacts = Controlador.getInstance().getCurrentContacts();
		list = new JList<String>();
		listModel = new DefaultListModel<String>();
		contacts.entrySet().stream().forEach(e -> {
			listModel.addElement(e.getKey());
		});
		List<Map.Entry<String, Integer>> entryList = new LinkedList<>();
		List<Integer> idList = entryList.stream().map(Entry::getValue).collect(Collectors.toList());
		list.setModel(listModel);
		imageMap = createImageMap(contacts);
		list.setBackground(SystemColor.controlDkShadow);
		list.setCellRenderer(new ListRenderer());
		list.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent evt) {
				JList<String> list = (JList<String>) evt.getSource();
				int index = list.locationToIndex(evt.getPoint());
				if (index >= 0 && index <= list.getModel().getSize()) {
					int id = idList.get(index);
					try {
						Controlador.getInstance().setCurrentChat(id);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(250, 400));
		this.add(scroll);
		this.setVisible(true);
		Timer timer = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					DefaultListModel<String> newListModel = new DefaultListModel<String>();
					Map<String, Integer> updatedContacts = Controlador.getInstance().getCurrentContacts();
					updatedContacts.entrySet().stream().forEach(entry -> {
						newListModel.addElement(entry.getKey());
					});
					List<?> elements1 = Arrays.asList(newListModel.toArray());
					List<?> elements2 = Arrays.asList(listModel.toArray());
					if (!elements1.equals(elements2)) {
						listModel.clear();
						listModel = newListModel;
						list.setModel(listModel);
						imageMap = createImageMap(updatedContacts);
						repaint();
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		timer.start();
	}

	public class ListRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;
		Font font = new Font("Open Sans", Font.PLAIN, 20);

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			String c = (String) value;
			JLabel label = (JLabel) super.getListCellRendererComponent(list, c, index, isSelected, cellHasFocus);
			label.setIcon(imageMap.get(c));
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setIconTextGap(20);
			label.setFont(font);
			return label;
		}

	}

	private Map<String, ImageIcon> createImageMap(Map<String, Integer> contacts) {
		Map<String, ImageIcon> map = new HashMap<>();
		contacts.entrySet().stream().forEach(e -> {
			ImageIcon imageIcon = null;
			try {
				String picture;
				picture = Controlador.getInstance().getContactPicture(e.getValue());
				if (picture == null)
					picture = "https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png";
				imageIcon = new ImageIcon(new URL(picture));
			} catch (MalformedURLException | InstantiationException | IllegalAccessException
					| ClassNotFoundException exception) {
				exception.printStackTrace();
			}
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			map.put(e.getKey(), imageIcon);
		});
		return map;
	}
}