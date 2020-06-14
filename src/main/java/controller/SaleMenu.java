package controller;

import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;

import java.io.FileNotFoundException;


public class SaleMenu {
    //gson
    public static void processOffs() throws FileNotFoundException {
        OutputHandler.showOffs();
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        CommandProcessor.setMenuStatus(MenuStatus.SALEMENU);
    }

}