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
import javax.swing.Timer;
import javax.swing.*;
import controlador.Controlador;
import modelo.Contacto;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;

public class ContactView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int DELAY = 1000;
	private Map<String, ImageIcon> imageMap;
	private DefaultListModel<Contacto> listModel;
	private JList<Contacto> list;

	public ContactView() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		super(new BorderLayout());
		List<Contacto> contacts = Controlador.getInstance().getCurrentContacts();
		list = new JList<Contacto>();
		listModel = new DefaultListModel<Contacto>();
		for (int i = 0; i < contacts.size(); i++)
			listModel.addElement(contacts.get(i));
		list.setModel(listModel);
		imageMap = createImageMap(contacts);
		list.setBackground(Color.LIGHT_GRAY);
		list.setCellRenderer(new ListRenderer());

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<Contacto> list = (JList<Contacto>) evt.getSource();
				int index = list.locationToIndex(evt.getPoint());
				if (index >= 0 && index <= list.getModel().getSize()) {
					Contacto c = (Contacto) list.getModel().getElementAt(index);
					try {
						Controlador.getInstance().setCurrentChat(c);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300, 400));
		this.add(scroll);
		this.setVisible(true);
		Timer timer = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				DefaultListModel<Contacto> newListModel = new DefaultListModel<Contacto>();
				for (int i = 0; i < contacts.size(); i++)
					newListModel.addElement(contacts.get(i));
				List<?> elements1 = Arrays.asList(newListModel.toArray());
				List<?> elements2 = Arrays.asList(listModel.toArray());
				if (!elements1.equals(elements2)) {
					listModel.clear();
					listModel = newListModel;
					list.setModel(listModel);
					imageMap = createImageMap(contacts);
					repaint();
				}
			}
		});
		timer.start();
	}

	public class ListRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;
		Font font = new Font("helvitica", Font.PLAIN, 16);

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Contacto c = (Contacto) value;
			JLabel label = (JLabel) super.getListCellRendererComponent(list, c.getName(), index, isSelected,
					cellHasFocus);
			label.setIcon(imageMap.get(c.getName()));
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(font);
			return label;
		}

	}

	private Map<String, ImageIcon> createImageMap(List<Contacto> contacts) {
		Map<String, ImageIcon> map = new HashMap<>();
		ImageIcon imageIcon = null;
		for (Contacto c : contacts) {
			try {
				String picture = c.getPicture();
				if (picture == null)
					picture = "https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png";
				imageIcon = new ImageIcon(new URL(picture));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			map.put(c.getName(), imageIcon);
		}
		return map;
	}
}