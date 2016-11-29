package view.admin;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import controller.Controller;
import models.entities.Product;
import models.entities.Shop;
import models.entities.User;

public class MainWindowAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private ToolBar toolbar;
	private TableUser tableUser;
	private TableShop tableShop;
	private TableProducts tableProducts;
	private PaginatePanel paginatePanel;

	public MainWindowAdmin(Controller controller) {
		setLayout(new BorderLayout());
		setSize(500, 500);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("RestaurantSoft Admin v0.1");
		setIconImage(new ImageIcon("src/img/icon.png").getImage());
		toolbar = new ToolBar(controller);
		add(toolbar, BorderLayout.PAGE_START);

		JTabbedPane objects = new JTabbedPane();

		tableUser = new TableUser(controller);
		tableShop = new TableShop(controller);
		tableProducts = new TableProducts(controller);

		objects.addTab("Shops", tableShop);
		objects.addTab("Users", tableUser);
		objects.addTab("Products", tableProducts);
		add(objects);
		
		paginatePanel = new PaginatePanel(controller);
		add(paginatePanel, BorderLayout.PAGE_END);

		setVisible(true);
	}

	public void refreshTableShop(ArrayList<Shop> shops) {
		tableShop.refreshTable(shops);
	}

	public void refreshTableUser(ArrayList<User> users) {
		tableUser.refreshTable(users);
	}

	public void refreshTableProducts(ArrayList<Product> listProducts) {
		tableProducts.refreshTable(listProducts);
	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}