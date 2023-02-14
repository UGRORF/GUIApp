package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JMenuBar{
    private MenuListener eHandler = new MenuListener();
    private JMenu windows;
    private JMenuItem window1, window2;
    private Frame frame;

    public Menu(Frame frame){
        super();
        if(frame.getClass() == App.class)
            this.frame = (App) frame;
        else if (frame.getClass() == SecondFrame.class)
            this.frame = (SecondFrame) frame;

        windows = new JMenu("Окна");
        window1 = new JMenuItem("Окно 1");
        window2 = new JMenuItem("Окно 2");
        windows.add(window1);
        windows.add(window2);
        windows.addSeparator();
        window1.addActionListener(eHandler);
        window2.addActionListener(eHandler);
        this.add(windows);
    }


    public class MenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == window1){
                App app = new App();
                app.setSize(600, 400);
                app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                app.setLocationRelativeTo(null);
                app.setVisible(true);
                frame.dispose();
            }

            if(e.getSource() == window2){
                SecondFrame app = null;
                try {
                    app = new SecondFrame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                app.setSize(600, 400);
                app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                app.setLocationRelativeTo(null);
                app.setVisible(true);
                frame.dispose();
            }
        }
    }
}
