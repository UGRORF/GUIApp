package src;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        App app = new App();
        app.setSize(600, 400);

        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);
        app.setVisible(true);

        ThreadNotebooks threadNotebooks = new ThreadNotebooks("GraphanaNotebooks");
        ThreadPlanshets threadPlanshets = new ThreadPlanshets("GraphanaPlanshets");
    }
}
