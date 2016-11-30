package view.user;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.Controller;
import models.entities.AssignmentProductShop;
import models.entities.Product;

public class MainWindowUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private ToolBar toolBarUser;
	private PanelForRestaurants panelForRestaurants;
	private JPanel panelActualCenter;
	private PanelProductsFromShop panelProductsFromShop;

	public MainWindowUser(Controller controller) {
		setSize(380, 600);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setLocationRelativeTo(null);
		setTitle("RestaurantSoft Client v0.01");
		getContentPane().setBackground(ConstanstUIUser.BACKGROUND_COLOR_MAIN_WINDOW_USER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		toolBarUser = new ToolBar(controller);
		add(toolBarUser, BorderLayout.NORTH);

		panelActualCenter = new JPanel();
		panelProductsFromShop = new PanelProductsFromShop(controller);
		panelForRestaurants = new PanelForRestaurants(controller);
		panelActualCenter.add(panelForRestaurants);
		add(new JScrollPane(panelActualCenter), BorderLayout.CENTER);

//		 setVisible(true);
	}

	public void refreshCardRestaurant(Controller controller) {
		panelForRestaurants.refreshCardRestaurant(controller);
	}
	
	public void changeToProductsFromShopPanel(ArrayList<Product> productsFromShop) {
		panelActualCenter.removeAll();
		panelProductsFromShop.refreshProducts(productsFromShop);
		panelActualCenter.add(panelProductsFromShop);
		panelActualCenter.updateUI();
		panelActualCenter.repaint();
	}
}