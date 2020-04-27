//package controller.menus;
//
//import model.accounts.Account;
//import model.accounts.Customer;
//import model.accounts.Manager;
//import model.accounts.Seller;
//import view.CommandProcessor;
//import view.SubMenuStatus;
//
//
//public class RegisterMenu {
//    private int outputNo;
//    private int inputNo;
//    private Account account;
//    private CommandProcessor commandProcessor;
//    private int detailMenu = 0;
//
//
//    public void processRegister(String role, String username) {
//        if (username.matches(" ")) {
//            if (!account.isThereAccountWithUsername(username)) {
//                registerByRole(role, username);
//                commandProcessor.setSubMenuStatus(SubMenuStatus.SETDETAILS);
//            } else outputNo = 3;
//        } else outputNo = 2;
//    }
//
//    private void registerByRole(String role, String username) {
//        if (role.equalsIgnoreCase("customer")) {
//            Customer newCustomer = new Customer(username);
//        } else if (role.equalsIgnoreCase("manager")) {
//            Manager newManager = new Manager(username);
//        } else if (role.equalsIgnoreCase("seller")) {
//            Seller newSeller = new Seller(username);
//        }
//    }
//
//    public void completeRegisterProcess(String detail) {
//        if (detailMenu == 0) {
//            account.setPassword(detail);
//            detailMenu++;
//        } else if (detailMenu == 1) {
//            account.setName(detail);
//            detailMenu++;
//        } else if (detailMenu == 2) {
//            account.setLastname(detail);
//            detailMenu++;
//        } else if (detailMenu == 3) {
//            account.setEmail(detail);
//            detailMenu++;
//        } else if (detailMenu == 4) {
//            account.setPhoneNo(detailMenu);
//            detailMenu = 0;
//        }
//    }
//
//
//}
