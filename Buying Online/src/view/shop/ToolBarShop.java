package view.shop;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import controller.ActionsShop;
import controller.ControllerShop;

public class ToolBarShop extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JTextField txSearch;

	public ToolBarShop(ControllerShop controllershop) {
		setBackground(ConstantsUIShop.COLOR_BACKGROUND_LOGIN);

		JButton btnAddProduct = new JButton(new ImageIcon(getClass().getResource("/img/2.png")));
		btnAddProduct.setToolTipText(ConstantsUIShop.ADD_PRODUCT);
		btnAddProduct.setActionCommand(ActionsShop.SHOW_ADD_PRODUCT_FROM_SHOP_VIEW_DIALOG.toString());
		btnAddProduct.setBackground(Color.decode("#FFFFFF"));
		btnAddProduct.addActionListener(controllershop);
		add(btnAddProduct);

		addSeparator();
		addSeparator();

		txSearch = new JTextField();
		add(txSearch);

		addSeparator();
		addSeparator();

		JButton btnSearch = new JButton("Search");
		btnSearch.setToolTipText("Search");
		btnSearch.setBackground(Color.decode("#FFFFFF"));
		add(btnSearch);

		addSeparator();
		addSeparator();

		setFloatable(false);
	}

	public String getTextSearch() {
		return txSearch.getText();
	}
}