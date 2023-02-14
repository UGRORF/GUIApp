package src;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ThreadPlanshets implements Runnable {
    Thread thrd;
    ChromeOptions options;
    ChromeDriver driver;
    String name;

    public ThreadPlanshets(String name) throws IOException {
        this.thrd = new Thread(this, name);
        this.name = name;

        options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("--headless");
        options.addArguments("--disable-extensions"); //to disable browser extension popup

        ChromeDriverService driverService = ChromeDriverService.createDefaultService();
        driver = new ChromeDriver(driverService, options);

        Map<String, Object> commandParams = new HashMap<>();
        commandParams.put("cmd", "Page.setDownloadBehavior");
        Map<String, String> params = new HashMap<>();
        params.put("behavior", "allow");
        params.put("downloadPath", "C:\\Downloads\\DownloadPlanshets");
        commandParams.put("params", params);
        ObjectMapper objectMapper = new ObjectMapper();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String command = objectMapper.writeValueAsString(commandParams);
        String u = driverService.getUrl().toString() + "/session/" + driver.getSessionId() + "/chromium/send_command";
        HttpPost request = new HttpPost(u);
        request.addHeader("content-type", "application/json");
        request.setEntity(new StringEntity(command));
        httpClient.execute(request);

        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar.get(Calendar.MONTH));

        this.thrd.start();
    }

    @Override
    public void run() {
        driver.get("http://s41-grafana.regions.alfaintra.net/d/QDHebGanz/uchet-planshetov?orgId=1&var-RCS=%D0%A6%D0%B5%D0%BD%D1%82%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9%20%D0%B4%D0%B8%D0%B2%D0%B8%D0%B7%D0%B8%D0%BE%D0%BD&var-City=%D0%A0%D0%BE%D1%81%D1%82%D0%BE%D0%B2-%D0%BD%D0%B0-%D0%94%D0%BE%D0%BD%D1%83&var-GDate_start=2022-01-31&var-GDate_fin=%D0%A1%D0%B5%D0%B3%D0%BE%D0%B4%D0%BD%D1%8F&var-RCSCity=%D0%A0%D0%BE%D1%81%D1%82%D0%BE%D0%B2-%D0%BD%D0%B0-%D0%94%D0%BE%D0%BD%D1%83&kiosk&viewPanel=42");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/div/main/div[3]/div/div/div[1]/div/div/div[1]/div/div/div[28]/div/section/div[1]/header/div/h2")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/div/main/div[3]/div/div/div[1]/div/div/div[1]/div/div/div[28]/div/section/div[1]/header/div/div[2]/div/ul/li[3]/a/span[1]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[2]/div[1]/div[1]/button/span")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
