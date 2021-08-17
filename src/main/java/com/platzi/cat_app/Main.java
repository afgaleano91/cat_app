package com.platzi.cat_app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int optionMenu = -1;
        String[] buttons = {
                "1. Ver gatos",
                "2. Salir"
        };

        do {
            //Menu principal
            String option = (String) JOptionPane.showInputDialog(null,
                    "Gatitos Java",
                    "Menu principal",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    buttons,
                    buttons[0]);
            // Validamos que opcion selecciona el usuario
            for (int i = 0; i < buttons.length; i++) {
                if(option.equals(buttons[i])){
                    optionMenu = i;
                }
            }

            switch (optionMenu){
                case 0:
                    CatsService.seeCats();
                    break;
                default:
                    break;
            }

        }while (optionMenu !=1);
    }
}
