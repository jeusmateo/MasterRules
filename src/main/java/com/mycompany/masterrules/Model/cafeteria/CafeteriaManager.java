package com.mycompany.masterrules.Model.cafeteria;


import com.mycompany.masterrules.Model.storage.StockInfo;

@Deprecated
public class CafeteriaManager {

    private final CafeteriaMenu menu;

    public CafeteriaManager() {
        this.menu = new CafeteriaMenu();
    }

    public CafeteriaManager(CafeteriaMenu menu) {
        this.menu = menu;
    }

    //se modifico acorde al nuevo storage
    public void createProduct(Product newProduct, boolean inInventory, StockInfo stockInfo) throws Exception {
        //se modifico la excepcion
        if (menu.isProductNameTaken(newProduct.getName())) {
            throw new Exception("ERROR: El nombre del producto ya está tomado");
        }
        menu.addProductToMenu(newProduct);

    }

    public void deleteProduct(Product product) throws Exception {//cambie param por product
        menu.removeProductOnMenu(product.getId());//cambie por id

    }

    public void editProduct(Product product, boolean inInventory, StockInfo stockInfo) throws Exception {//cambie la cantidad por el stockInfo

        menu.editProduct(product);

    }

    public void createCombo(Combo newCombo) throws Exception {
        if (menu.isComboNameTaken(newCombo.getName())) {
            throw new Exception("ERROR: El nombre del combo ya está tomado");
        }

        menu.addComboToMenu(newCombo);
    }


    public void deleteCombo(String comboID) throws Exception {
        menu.removeComboOnMenu(comboID);
    }

    public void editCombo(Combo combo) throws Exception {
        menu.editCombo(combo);
    }

    public void editMenu() {//creo que ya no vamos a utilizar esta, a menos que se quiera
        //falta
    }

    public CafeteriaMenu getMenu() {
        return menu;
    }

}
