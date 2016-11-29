package view.admin;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.Actions;
import controller.Controller;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;

	public ToolBar(Controller controller) {

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setToolTipText("Add estudent");
		// btnAddUser.setActionCommand(Actions.SHOW_ADD_USER_DIALOG.toString());
		btnAddUser.addActionListener(controller);
		add(btnAddUser);

		JButton btnAddShop = new JButton("Add Shop");
		btnAddShop.setToolTipText("Add Shop");
		btnAddShop.setActionCommand(Actions.SHOW_ADD_SHOP_DIALOG.toString());
		btnAddShop.addActionListener(controller);
		add(btnAddShop);

		setFloatable(false);
	}
}