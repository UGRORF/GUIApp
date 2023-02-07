package src;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SecondFrame extends Frame {
    private String[] columnNames = {};
    private Object[][] data= {};
    Menu menu;
    JLabel l1;
    JTextField t1;
    JButton b1;
    JTable table1;
    GroupLayout layout;

    public SecondFrame() throws IOException {
        super();
        columnNames = new String[]{"1", "2", "3", "4"};
        data = new Object[][] {{"veeefe", "veveve", "221312", "111"},
                {"veeefe", "veveve", "221312", "111"},
                {"veeefe", "veveve", "221312", "111"},
                {"veeefe", "veveve", "221312", "111"},
                {"veeefe", "veveve", "221312", "111"},
                {"veeefe", "veveve", "221312", "111"},
                {"veeefe", "veveve", "221312", "111"},
        };

        //Меню
        menu = new Menu(this);
        this.setJMenuBar(menu);

        l1 = new JLabel("ФИО");
        t1 = new JTextField(30);
        b1 = new JButton("Найти");
        JPanel contents = new JPanel(new BorderLayout());

        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.add(l1);
        jPanel.add(t1);
        jPanel.add(b1);
        contents.add(jPanel, BorderLayout.NORTH);

        JPanel jPanel1 = new JPanel(new BorderLayout());
        table1 = new JTable(new TableModel(data, columnNames));
        table1.setFillsViewportHeight(true);
        jPanel1.add(table1.getTableHeader(), BorderLayout.PAGE_START);
        jPanel1.add(table1, BorderLayout.CENTER);
        contents.add(jPanel1, BorderLayout.CENTER);
        add(contents);

        EdgeOptions options = new EdgeOptions();
        options.addArguments("headless");
        EdgeDriver driver = new EdgeDriver(options);
        String str = "";
        driver.get("http://search/Pages/view.aspx#/all?query="+ str);
        driver.findElement(By.className("command_user_name_text_hover")).click();
        String name = driver.findElement(By.xpath("/html/body/form/div[10]/div[1]/div/div[2]/div[2]/div[3]" +
                "/div[1]/div[2]/div[1]/div[1]/div/div/div/div/div[1]/app-root/div/app-user-info/div" +
                "/div[2]/div[2]/div/app-main-user-data/div/div[2]")).getText();
        t1.setText(name);
    }
}
