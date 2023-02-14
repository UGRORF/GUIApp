package src;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;


public class ThreadHandbook extends Thread{
    Thread thrd;
    String name;
    private ChromeOptions options;
    private static ChromeDriver driver;

    public ThreadHandbook(String name){
        this.thrd = new Thread(this, name);
        this.name = name;
        this.options = new ChromeOptions();
        options.addArguments("headless");
        this.driver = new ChromeDriver(options);
        this.thrd.start();
    }

    @Override
    public void run(){
        try {

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Пользователь не найден");
        }
    }

    public static ChromeDriver getDriver() {
        return driver;
    }
}
