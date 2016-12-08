package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import models.dao.ManagerAsingProduct;
import models.dao.ManagerShop;
import models.entities.Product;
import models.entities.Shop;
import models.exceptions.ErrorShopNotFound;
import persistence.ReadXML;
import view.admin.AddShopDialog;
import view.admin.MainWindowAdmin;
import view.login.DialogLogin;
import view.shop.AddProductFromShopViewDialog;
import view.shop.MainWindowShop;

public class ControllerShop implements ActionListener, KeyListener, ChangeListener {

	private DialogLogin dialogoLogin;
	private ManagerShop managerShop;
	private MainWindowAdmin mainWindowAdmin;
	private MainWindowShop mainWindowShop;
	private AddShopDialog addShopDialog;
	private AddProductFromShopViewDialog addProductFromShopViewDialog;
	private ManagerAsingProduct managerAsingProduct;
	private GeneralController generalController;
	private int actualPage;

	public ControllerShop(GeneralController generalController) {
		this.generalController = generalController;
		actualPage = 1;
//		 dialogoLogin = new DialogLogIn();
		managerShop = new ManagerShop();
		mainWindowShop = new MainWindowShop(this);
//		addShopDialog = new AddShopDialog(mainWindowShop, this);
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		switch (ActionsShop.valueOf(arg.getActionCommand())) {
		case ADD_PRODUCT_TO_SHOP:
			try {
				addProductToShop();
			} catch (NumberFormatException | ParserConfigurationException | SAXException | IOException e1) {
				e1.printStackTrace();
			}
			break;
		case CHARGE_IMAGE_PRODUCT_FROM_SHOP_VIEW:
			chargeImageProductFromShopView();
			break;
		case EDIT_PRODUCT_TO_SHO:

			break;
		case LOG_OUT:

			break;
		case SHOW_ADD_PRODUCT_FROM_SHOP_VIEW_DIALOG:
			showAddProductFromShopViewDialog();
			break;
		case ADD_IMAGE_TO_SHOP:
			addShopDialog.openFileChooser();
			break;
		case ADD_SHOP:

			break;
		case SHOP_VIEW:

			break;
		case SHOW_ADD_SHOP_DIALOG:
			addShopDialog.setVisible(true);
			break;
		case EDIT_SHOP:
			try {
				editShop();
			} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void run() {
		dialogoLogin.setVisible(true);
	}

	private void editShop() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		try {
			managerShop.editShop(addShopDialog.getShop(), managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			// refreshList(0);
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			ReadXML.writeShop(managerShop.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}

	private void showAddProductFromShopViewDialog() {
		addProductFromShopViewDialog.showToAddProduct();
		addProductFromShopViewDialog.setVisible(true);
	}

	private void chargeImageProductFromShopView() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		addProductFromShopViewDialog.addImage(file.getAbsolutePath());
	}

	private void addProductToShop()
			throws NumberFormatException, ParserConfigurationException, SAXException, IOException {
		Product createdProduct = addProductFromShopViewDialog.getCreatedProduct();
		managerAsingProduct.addAssignmentProductShop(
				ManagerAsingProduct.createAssignmentProductShop(createdProduct, getActualShop()));
		mainWindowShop.refreshProductsTable(managerAsingProduct.getProductsListFromShop(getActualShop()));
		addProductFromShopViewDialog.setVisible(false);
	}

	private Shop getActualShop() {
		return findTheShopHelper();
	}

	public Shop findTheShopHelper() {
		Shop shop = null;
		try {
			shop = managerShop.searchShopName(dialogoLogin.getUsername());
		} catch (ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
		return shop;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setVi() {
		mainWindowShop.setVi();
	}
}
