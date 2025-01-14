package GUI;

import Clases.Usuario;

public class MainGUI {
    public static void main(String[] args){

        PanelManager panelManager = new PanelManager();
        panelManager.armarPanelManager();
        panelManager.mostrarLoginRegister();

    }
}
