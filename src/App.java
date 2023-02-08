package src;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class App extends Frame{
    Menu menu;
    JButton b1, b2, b3, b4;
    JLabel l1, l2, l3, l4, l5, l6, l7;
    ButtonGroup buttonGroup;
    JRadioButton radioButton1, radioButton2;
    JTextField t1, t2, t3, t4, t5;
    private final String path1, path2;
    GroupLayout layout;
    eHandler handler;
    EdgeOptions options;
    EdgeDriver driver;

    public App(){
        super();



        //Меню
        menu = new Menu(this);
        this.setJMenuBar(menu);

        handler = new eHandler();


        //Path to files
        path1 = "";
        path2 = "";

        radioButton1 = new JRadioButton("Ноутбук");
        radioButton1.doClick();
        radioButton2 = new JRadioButton("Планшет");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);


        l1 = new JLabel("Серийный номер");
        t1 = new JTextField(10);
        l2 = new JLabel("");
        b1 = new JButton("Найти");
        b4 = new JButton("Найти1");

        t2 = new JTextField();
        l3 = new JLabel("");
        t3 = new JTextField();
        l4 = new JLabel("");
        t4 = new JTextField();
        l5 = new JLabel("");
        t5 = new JTextField();
        l6 = new JLabel("");
        l7 = new JLabel("");


        b2 = new JButton("Применить");
        b3 = new JButton("Отмена");



        // Определение менеджера расположения
        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // Создание горизонтальной группы
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(l1)
                        .addComponent(l2)
                        .addComponent(l3)
                        .addComponent(l4)
                        .addComponent(l5)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(t1)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(radioButton1))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(radioButton2)))
                        .addComponent(t2)
                        .addComponent(t3)
                        .addComponent(t4)
                        .addComponent(t5)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(b2)
                                .addComponent(b3)
                                .addComponent(l6))

                )

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(b1)
                        .addComponent(l7))

        );

        layout.linkSize(SwingConstants.HORIZONTAL, b1);

        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l1)
                        .addComponent(t1)
                        .addComponent(b1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(radioButton1)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(radioButton2)))
                        ))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l2)
                        .addComponent(t2)
                        .addComponent(l7))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l3)
                        .addComponent(t3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l4)
                        .addComponent(t4))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l5)
                        .addComponent(t5))
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(b2)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(b3))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(l6)))
                )
        );
        t2.hide();
        t3.hide();
        t4.hide();
        t5.hide();
        b2.hide();
        b3.hide();
        l7.hide();

        addListener();

    }

    private void addListener(){
        b1.addActionListener(handler);
        b2.addActionListener(handler);
        b3.addActionListener(handler);
        radioButton1.addKeyListener(handler);
        radioButton2.addKeyListener(handler);
        l1.addKeyListener(handler);
        t1.addKeyListener(handler);
        l2.addKeyListener(handler);
        b1.addKeyListener(handler);
        t2.addKeyListener(handler);
        t3.addKeyListener(handler);
        l3.addKeyListener(handler);
        t4.addKeyListener(handler);
        l4.addKeyListener(handler);
        t5.addKeyListener(handler);
        l5.addKeyListener(handler);
        l6.addKeyListener(handler);
        b2.addKeyListener(handler);
        b3.addKeyListener(handler);
    }

    public class eHandler implements ActionListener, KeyListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                t1.setText(t1.getText().replaceAll(" ", ""));
                if ((e.getSource() == b1) && (t1.getText().length() != 0)){
                    if(t1.getText().matches("[^A-Za-z0-9]"))
                    {
                        b3.doClick();
                        JOptionPane.showMessageDialog(
                                null,
                                "Введены некорректные символы, разрешенные для использования A-Z,a-z,0-9"
                        );
                    } else{
                        show();
                        if(radioButton1.isSelected()){
                            read(path1);
                        }
                        if(radioButton2.isSelected()){
                            read(path2);
                        }
                    }
                }

                if(e.getSource() == b2){
                    if(radioButton1.isSelected()){
                        write(path1);
                    }
                    if(radioButton2.isSelected()){
                        write(path2);
                    }
                }

                if (e.getSource() == b3){
                    hide();
                }

            }catch (Exception ex){
                System.out.println(ex.getMessage());
                b3.doClick();
                JOptionPane.showMessageDialog(null, "Файл не найден");}
        }

        private void read(String path) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(path);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            boolean f = false;

            for(Row row : sheet) {
                f = t1.getText().toLowerCase(Locale.ROOT).equals(row.getCell(3).toString().toLowerCase(Locale.ROOT));
                if(f){
                    t2.setText(String.valueOf(row.getCell(4)));
                    t3.setText(String.valueOf(row.getCell(5)));
                    t4.setText(String.valueOf(row.getCell(6)));
                    t5.setText(String.valueOf(row.getCell(8)));
                    l6.setText(String.valueOf(row.getCell(7)));
                    l7.setText("№ " + String.valueOf(row.getRowNum()));
                    break;
                }
            }
            if(!f){
                b3.doClick();
                JOptionPane.showMessageDialog(null, "Техника не найдена");
            }

            fileInputStream.close();
            xssfWorkbook.close();
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                b1.doClick();
            }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                b3.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        private void write(String path) throws IOException{
            FileInputStream fileInputStream = new FileInputStream(path);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = xssfWorkbook.getSheetAt(0);

            try {
                options = new EdgeOptions();
                options.addArguments("headless");
                driver = new EdgeDriver();
                String str = t2.getText();
                str = str.replaceAll(" ", "* ");
                System.out.println(str);
                driver.get("http://alfa/search/Pages/view.aspx#/all?query=" + str);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.findElement(By.className("command_user_name_text_hover")).click();
                String position = driver.findElement(By.xpath("/html/body/form/div[10]/div[1]/div/div[2]/div[2]/div[3]" +
                        "/div[1]/div[2]/div[1]/div[1]/div/div/div/div/div[1]/app-root/div/app-user-info/div" +
                        "/div[2]/div[2]/div/app-main-user-data/div/div[2]")).getText();
                String department = driver.findElement(By.xpath("/html/body/form/div[10]/div[1]" +
                        "/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div/div/div/div/div[1]/app-root/div" +
                        "/app-user-info/div/div[1]/div/div[2]/div/div")).getText();

                t3.setText(position);
                t4.setText(department);
                driver.quit();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Пользователь не найден");
            }


            for(Row row : sheet) {
                if(t1.getText().toLowerCase(Locale.ROOT).equals(row.getCell(3).toString().toLowerCase(Locale.ROOT))){
                    row.getCell(4).setCellValue(t2.getText());
                    row.getCell(5).setCellValue(t3.getText());
                    row.getCell(6).setCellValue(t4.getText());
                    row.getCell(8).setCellValue(t5.getText());
                    break;
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            xssfWorkbook.write(fileOutputStream);
            fileInputStream.close();
            fileOutputStream.close();
            xssfWorkbook.close();
        }

        private void show(){
            l2.setText(t1.getText());
            l2.setText("ФИО");
            l3.setText("Должность");
            l4.setText("Подразделение");
            l5.setText("Статус");
            t2.show();
            t3.show();
            t4.show();
            t5.show();
            b2.show();
            b3.show();
            l7.show();
        }

        private void hide(){
            l2.setText(t1.getText());
            l2.setText("");
            l3.setText("");
            l4.setText("");
            l5.setText("");
            l6.setText("");
            t2.hide();
            t2.setText("");
            t3.hide();
            t3.setText("");
            t4.hide();
            t4.setText("");
            t5.hide();
            t5.setText("");
            b2.hide();
            b3.hide();
            l7.hide();
        }
    }
}
