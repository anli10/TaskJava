import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;



public class TestBase {



    protected static HomePage homePage;
    private  static WebDriver webDriver;

    @BeforeClass
    public static void before(){

        System.setProperty("webdriver.chrome.driver","C:/Users/bghiuta/Documents/My Received Files/chromedriver.exe");
        webDriver=new ChromeDriver();
        webDriver.get("http://192.168.164.15/");
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }

    @AfterClass
    public static void after(){
        //webDriver.close();
    }
}
