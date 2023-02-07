package src;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.setSize(600, 400);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}
