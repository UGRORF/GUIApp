package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class SecondFrame extends Frame {
    private String[] columnNames;
    private Object[][] data;
    Menu menu;
    JLabel l1;
    JTextField t1;
    JButton b1;
    JTable table1;
    JPanel jPanel, jPanel1, contents;
    eHandler handler;

    public SecondFrame() throws IOException {
        super();
        handler = new eHandler();
        columnNames = new String[]{"ЕО", "S/N", "Описание", "Пользователь"};
        data = new Object[0][columnNames.length];

        //Меню
        menu = new Menu(this);
        this.setJMenuBar(menu);

        l1 = new JLabel("ФИО");
        t1 = new JTextField(30);
        b1 = new JButton("Найти");
        contents = new JPanel(new BorderLayout());

        jPanel = new JPanel(new FlowLayout());
        jPanel.add(l1);
        jPanel.add(t1);
        jPanel.add(b1);
        contents.add(jPanel, BorderLayout.NORTH);

        jPanel1 = new JPanel(new BorderLayout());
        table1 = new JTable(new TableModel(data, columnNames));
        table1.setFillsViewportHeight(true);
        jPanel1.add(table1.getTableHeader(), BorderLayout.PAGE_START);
        jPanel1.add(table1, BorderLayout.CENTER);
        contents.add(jPanel1, BorderLayout.CENTER);
        add(contents);

        b1.addActionListener(handler);
    }

    public class eHandler implements ActionListener, KeyListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == b1){
                data = new Object[0][columnNames.length];
                read("C:\\Downloads\\DownloadPlanshets");
                read("C:\\Downloads\\DownloadNotebooks");
                table1.setModel(new TableModel(data, columnNames));
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        private void read(String path){
            try (DirectoryStream<Path> files = Files.newDirectoryStream(Path.of(path))) {
                String path1 = "";
                for (Path file : files) {
                    path1 = file.toString();
                }

                BufferedReader csvReader = new BufferedReader(new FileReader(path1));
                String row;
                String[] dataOfFile;
                String[] fio, fio1;

                while ((row = csvReader.readLine()) != null) {
                    dataOfFile = row.replaceAll(", ", ".").split(",");

                    if(dataOfFile.length > 1){
                        for (int j = 0; j < dataOfFile.length; j++) {
                            fio = t1.getText().toLowerCase(Locale.ROOT).split(" ");
                            fio1 = dataOfFile[j].toLowerCase(Locale.ROOT).split(" ");
                            if(isAccepted(fio, fio1)){
                                data = addElement(dataOfFile);
                            }
                        }
                    }
                }
                csvReader.close();
            }catch (IOException ioException){
                JOptionPane.showMessageDialog(
                        null,
                        "Файл не готов, попробуйте позже"
                );
            }
        }

        private Object[][] addElement(String[] str){
            Object[][] newArr = new Object[data.length + 1][columnNames.length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < 4; j++) {
                    newArr[i][j] = data[i][j];
                }
            }
            newArr[data.length][0] = str[0];
            newArr[data.length][1] = str[3];
            newArr[data.length][2] = str[2];
            newArr[data.length][3] = str[6];

            return newArr;
        }
    }
}
