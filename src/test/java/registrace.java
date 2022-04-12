import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class registrace {
    private WebDriver driver;
    private final String URL = "https://www.kulturistika.com/buy/register";
    private String Email = "stejskal.@gmail.com";
    private String Heslo = "bohuš123";
    private String Jméno = "Bohumil";
    private String Příjmení = "Stejskal";
    private String Adresa = "Gorkého9";
    private String Město = "Brno";
    private String PSČ = "60200";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")));
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
    }

    @Test
    public void testPrazdnaPole()throws InterruptedException{

        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.name("agreement")));
        driver.findElement(By.name("agreement")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-kult g-recaptcha']")).click();
    }

    @Test
    public void testBezHesla()throws InterruptedException{

        driver.findElement(By.className("kult-textinput")).sendKeys(Email);
        driver.findElement(By.name("agreement")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-kult g-recaptcha']")).click();

        //Alert alert = driver.switchTo().alert();
        //alert.accept();
    }

    @Test
    public void testNeshodujícíSeHesla()throws InterruptedException{

        driver.findElement(By.className("kult-textinput")).sendKeys(Email);
        driver.findElement(By.name("password1")).sendKeys(Heslo);
        driver.findElement(By.name("password2")).sendKeys("bohuš333");
        driver.findElement(By.name("agreement")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-kult g-recaptcha']")).click();
    }

    @Test
    public void testChybíJméno()throws InterruptedException{

        driver.findElement(By.className("kult-textinput")).sendKeys(Email);
        driver.findElement(By.name("password1")).sendKeys(Heslo);
        driver.findElement(By.name("password2")).sendKeys(Heslo);
        driver.findElement(By.name("lastname")).sendKeys(Příjmení);
        driver.findElement(By.name("firmaddr1")).sendKeys(Adresa);
        driver.findElement(By.name("firmcity")).sendKeys(Město);
        driver.findElement(By.name("firmpostcode")).sendKeys(PSČ);

        driver.findElement(By.name("agreement")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-kult g-recaptcha']")).click();
    }

    @Test
    public void testNezaškrtnuObchPod()throws InterruptedException{

        driver.findElement(By.className("kult-textinput")).sendKeys(Email);
        driver.findElement(By.name("password1")).sendKeys(Heslo);
        driver.findElement(By.name("password2")).sendKeys(Heslo);
        driver.findElement(By.name("firstname")).sendKeys(Jméno);
        driver.findElement(By.name("lastname")).sendKeys(Příjmení);
        driver.findElement(By.name("firmaddr1")).sendKeys(Adresa);
        driver.findElement(By.name("firmcity")).sendKeys(Město);
        driver.findElement(By.name("firmpostcode")).sendKeys(PSČ);

        driver.findElement(By.xpath("//button[@class='btn btn-kult g-recaptcha']")).click();
    }

    @Test
    public void testUspěšnáRegistrace()throws InterruptedException{

        driver.findElement(By.className("kult-textinput")).sendKeys(Email);
        driver.findElement(By.name("password1")).sendKeys(Heslo);
        driver.findElement(By.name("password2")).sendKeys(Heslo);
        driver.findElement(By.name("firstname")).sendKeys(Jméno);
        driver.findElement(By.name("lastname")).sendKeys(Příjmení);
        driver.findElement(By.name("firmaddr1")).sendKeys(Adresa);
        driver.findElement(By.name("firmcity")).sendKeys(Město);
        driver.findElement(By.name("firmpostcode")).sendKeys(PSČ);

        driver.findElement(By.name("agreement")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-kult g-recaptcha']")).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//div[@class='alert alert-warning']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-warning']")).isDisplayed());
    }

    @After
    public void tearDown(){

        //driver.close();
        //driver.quit();
    }
}
