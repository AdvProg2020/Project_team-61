package controller.menus;

import controller.request.Request;
import model.accounts.Account;
import model.off.DiscountCode;
import model.productRelated.Category;
import model.productRelated.Product;

public class ManagerMenu {
    private int outputNo;
    private int inputNo;
    private Account account;
    private Product product;
    private DiscountCode discountCode;
    private Category category;
    private Request request;

    public void processManageUsers() {
        // OutputHandler.showObjectOutput(account.listUsers(), account.getAccountListSize(),1);
    }

    public void view(String username) {
        // OutputHandler.showObjectOutput(account.getAccountWithUsername(username) , 1,1);
    }

    public void deleteUser(String username) {
        account.deleteAccount(username);
    }

    public void createManagerProfile() {

    }

    public void processManageAllProducts() {
        //OutputHandler.showObjectOutput(product.getProductList(), product.getProductListSize(),1);
    }

    public void removeProduct(String productID) {
        //product.deleteProduct(productID);
    }

    public void processCreateDiscountCode() {
        // DiscountCode newDiscountCode = new DiscountCode(discountCode);
    }

    public void processViewDiscountCodes() {
        //  OutputHandler.showObjectOutput(discountCode.listDiscountCodes(), discountCode.getDiscountCodeListSize(),1);
    }

    public void viewDiscountCode(String DiscountCodeID) {
        //  OutputHandler.showObjectOutput(discountCode.getDiscountWithID(DiscountCodeID) , 1,1);
    }

    public void editDiscountCode(String DiscountCodeID) {

    }

    public void editDiscountCodeField(String edit){

    }

    public void removeDiscountCode(String DiscountCodeID) {
        //   discountCode.deleteDiscount(DiscountCodeID);
    }

    public void processManageRequests() {
        //  OutputHandler.showObjectOutput(request.listRequest(), request.getRequestListSize(),1);
    }

    public void detailsRequest(String requestID) {

    }

    public void acceptRequest(String requestID) {
        // request.setIsRequestAccepted(true);
    }

    public void declineRequest(String requestID) {
        //  request.setIsRequestAccepted(false);
    }

    public void processManageCategories() {
        //  OutputHandler.showObjectOutput(category.listCategory(), category.geyListCategorySize(),1);
    }

    public void editCategory(String category) {

    }

    public void editCategoryField(String edit){

    }

    public void addCategory(String category) {
        //  Category newCategory = new Category(category);
    }

    public void removeCategory(String category) {
        //  category.deleteCategory(category);
    }

}
