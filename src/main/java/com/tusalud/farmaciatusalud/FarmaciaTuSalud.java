package com.tusalud.farmaciatusalud;

import interfazmenu.InterfazAcceder;

/**
 *
 * @author codeli4m
 */
public class FarmaciaTuSalud {

    public static void main(String[] args) {
        System.out.println("Iniciando aplicacion!");
        InterfazAcceder login = new InterfazAcceder();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }
}
