package src;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public abstract class Frame extends JFrame {
    public Frame(){
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent event) {
                try (DirectoryStream<Path> folders = Files.newDirectoryStream(Path.of("C:\\Downloads"))) {
                    for (Path path: folders) {
                        DirectoryStream<Path> files = Files.newDirectoryStream(Path.of(String.valueOf(path)));
                        for (Path file : files) {
                            Files.delete(file);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

//    protected boolean isAccepted(String[] s, String[] s1){
//        boolean f = true;
//        for (int i = 0; i < s.length; i++) {
//            int k = s[i].length();
//            System.out.println(s1[i].toLowerCase(Locale.ROOT));
//            if(!s[i].toLowerCase(Locale.ROOT).equals(s1[i].substring(0,k-1).toLowerCase(Locale.ROOT)))
//                return false;
//
//        }
//        return f;
//    }

    protected boolean isAccepted(String[] s, String[] s1){
        boolean f = true;
        for (int i = 0; i < s.length; i++) {
            if(!s1[i].equals(s[i]))
                return false;
        }
        return f;
    }
}
