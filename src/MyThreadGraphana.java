package src;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class MyThreadGraphana extends Thread{
    private Object[][] data;

    public MyThreadGraphana(){
        this.data = new Object[][]{};
    }

    @Override
    public void run(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("headless");
        EdgeDriver driver = new EdgeDriver();


        try {
            driver.get("http://s41-grafana.regions.alfaintra.net/d/QDHebGanz/uchet-planshetov?orgId=1&var-RCS=%D0%A6%D0%B5%D0%BD%D1%82%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9%20%D0%B4%D0%B8%D0%B2%D0%B8%D0%B7%D0%B8%D0%BE%D0%BD&var-City=%D0%A0%D0%BE%D1%81%D1%82%D0%BE%D0%B2-%D0%BD%D0%B0-%D0%94%D0%BE%D0%BD%D1%83&var-GDate_start=2022-07-28&var-GDate_fin=%D0%A1%D0%B5%D0%B3%D0%BE%D0%B4%D0%BD%D1%8F&var-RCSCity=%D0%A0%D0%BE%D1%81%D1%82%D0%BE%D0%B2-%D0%BD%D0%B0-%D0%94%D0%BE%D0%BD%D1%83&var-PeriodReport=%D0%9C%D0%B5%D1%81%D1%8F%D1%86&var-PeriodAVGStr=01.02.23--06.02.23&var-StartPeriodAVG=2023-02-01&var-LastDateReport=2023-02-06&inspect=42&inspectTab=data");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.className("css-1mhnkuh")).click();
            driver.quit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Пользователь не найден");
        }
    }
}
