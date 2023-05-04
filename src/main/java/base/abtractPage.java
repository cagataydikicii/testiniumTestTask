package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utiliy.log;


import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;



public class abtractPage {

    log log = new log();
    private WebDriver driver;
    private WebDriverWait wait;


    public void openBrowser() {
        try {
            // WebDriver nesnesini başlat
            System.setProperty("webdriver.chrome.driver", "C:/chromeDriver/chromedriver.exe");
            driver = new ChromeDriver();
            this.driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("WebDriver başlatılamadı: " + e.getMessage());
        }
    }

    public void naviGateTo(String url) {
        try {
            // WebDriver nesnesi null değilse url'e git
            if (driver != null) {
                driver.get(url);
            }
        } catch (Exception e) {
            System.out.println("URL açılırken hata meydana geldi: " + e.getMessage());
        }
    }

    public void closeBrowser() {
        try {
            // WebDriver nesnesi null değilse tarayıcıyı kapat
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Tarayıcı kapatılırken hata meydana geldi: " + e.getMessage());
        }
    }
    public boolean isElementExist(By by){
        return isElementExist(by,10);
    }
    public boolean isElementExist(By by, int timeSeconds){
        driver.manage().timeouts().implicitlyWait(timeSeconds,TimeUnit.SECONDS);
        List<WebElement> links = driver.findElements(by);

        boolean isExist = links.size()>0;
        return isExist;
    }
    public void click(By by) throws InterruptedException {
        WebElement element;
        try {
            element = driver.findElement(by);
            element.click();

        }catch (Exception e){
            log.error("elemente tıklanamadı : "+e);
        }
    }

    public void sendKeys(By by, String text, boolean pressEnter){
        WebElement element;
        try {
            element = driver.findElement(by);
            if(element.isEnabled()){
                element.clear();
                element.sendKeys(text);
                if (pressEnter){
                    element.sendKeys(Keys.ENTER);
                }
            }

        }catch (Exception e){
            log.error("text yazımında hata alındı"+e);
        }


    }

    public void cleanText(By element1){
        WebElement element = driver.findElement(element1);
        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
    public void pageDownElement(By by){
        WebElement element = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeAsyncScript("arguments[0].scrollIntoView(true)",element);
    }
    public void selectRandomProductList() throws InterruptedException {
        try {
           int size = 0;
            size = sizeOfRandom(25);
            wait(3);
            click(By.xpath("(//*[@class='m-productCard__desc'])["+size+"]"));

        }catch (Exception e){
            log.info("Hata : "+e);
        }

    }

    public void clickJS(By by){

        WebElement element = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public int sizeOfRandom(int size) {
        Random rand = new Random();

        int random = rand.nextInt(1,size);
        log.info(String.valueOf(random));
        return random;

    }
    public void productNameAndPrice(By name, By price){
        String productName = driver.findElement(name).getText();
        String productPrice = driver.findElement(price).getText();
        String filePath = "C:\\testPath\\test.txt";
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.write(productName+"   " + productPrice);
            writer.close();
            log.info("Dosya başarıyla oluşturuldu ");
            log.info("Ürün adı : "+productName+" ve fiyat "+productPrice+"yazıldı.");
        } catch (Exception e) {
            log.info("Dosya oluşturulurken hata oluştu: " + e.getMessage());
        }

    }
    public String returnText(By text1){
        String text = driver.findElement(text1).getText();
        return text;
    }
    public void selectProductBox(By productSize){
        WebElement element = driver.findElement(productSize);
        Select select = new Select(element);
        try {
            select.selectByIndex(1);
            log.info("2 adet secildi");
        }catch (Exception e){
            log.info("ürün adedi seçilirken hata alındi");

        }
        String basketSize = element.getAttribute("value");
        log.info("basketSize = " + basketSize);


    }
    public void wait(int sec){
        try{
            Thread.sleep(1000*sec);
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }



}
