package ru.azolotuhin.calculator;

import ru.azolotuhin.calculator.Forms.AuthorizationForm;
import ru.azolotuhin.calculator.Forms.MainForm;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        //com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(AuthorizationForm::new);
       //SwingUtilities.invokeLater(MainForm::new);
    }
}
