package controller.menus;


import model.firms.Firm;
import model.off.Sale;
import model.productRelated.Product;
import view.CommandProcessor;
import view.OutputHandler;
import view.SubMenuStatus;

public class SellerMenu {
    private int outputNo;
    private int inputNo;
    private Sale sale;
    private Product product;
    private Firm firm;
    private CommandProcessor commandProcessor;


    public void processViewCompanyInformation() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMPANYINFORMATION);
    }

    public void viewCompanyInformation(String firmID) {
        OutputHandler.showObjectOutput(firm.getFirmWithID(firmID) , 1,1);
    }

    public void processViewSalesHistory() {

    }


    public void processManageProducts() {
         OutputHandler.showObjectOutput(product.getProductList (), product.getProductListSize(),1);

    }

    public void viewProduct(String productID){
         OutputHandler.showObjectOutput(product.getProductById (productID) , 1,1);

    }

    public void viewBuyersProduct(String productID){

    }

    public void editProduct(String productID){

    }

    public void editProductField(String edit){

    }

    public void processAddProduct() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.ADDPRODUCT);
    }

    public void addProduct(String ID){
        n
    }

    public void processRemoveProduct(String productID) {
        product.deleteProduct(productID);
    }

    public void processShowCategories() {

    }

    public void processViewOffs() {
         OutputHandler.showObjectOutput(sale.getSaleListSize(), sale.getListSaleSize(),1);
    }

    public void viewOff(String offID){
         OutputHandler.showObjectOutput(sale.getSaleWithID(offID) , 1,1);
    }

    public void editOff(String offID){

    }

    public void editOffField(String edit){

    }

    public void addOff(){

    }

    public void processViewBalance(){

    }
}
