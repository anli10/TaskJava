import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class HomePage {

    @FindBy(xpath=".//*[@id='top-links']/ul/li[2]")
    private WebElement myAccount;

    @FindBy(xpath=".//*[@id='top-links']/ul/li[2]/ul/li[1]/a")
    private WebElement registerButton;

    @FindBy(xpath=".//*[@id='top-links']/ul/li[2]/ul/li[5]/a")
    private WebElement logOutButton;

    @FindBy(xpath=".//*[@id='top-links']/ul/li[2]/ul/li[2]/a")
    private WebElement logInButton;

    @FindBy(id="logo")
    private WebElement HomeButton;

    @FindBy(xpath=".//*[@id='content']/div[2]/div")
    private List<WebElement> productList;

    @FindBy(xpath=".//*[@id='content']/div[2]/div[4]/div/div[2]/h4/a")
    private WebElement product;

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver){
        this.webDriver=webDriver;
    }

    Random randomizer = new Random();

    public WebElement getLogOutButton() {
        myAccount.click();
        return logOutButton;
    }

    public WebElement getHomeButton() {
        return HomeButton;
    }

    public WebElement getMyAccount() {
        return myAccount;
    }

    public RegisterPage register(){

        myAccount.click();
        registerButton.click();
        RegisterPage registerPage= PageFactory.initElements(webDriver,RegisterPage.class);
        return registerPage;
    }
    public void logOut(){
        myAccount.click();
        logOutButton.click();
    }
    public LogInPage logIn(){
        myAccount.click();
        logInButton.click();
        LogInPage logInPage= PageFactory.initElements(webDriver,LogInPage.class);
        return logInPage;
    }
    public ProductPage selectProduct(){
        //WebElement random = productList.get(randomizer.nextInt(productList.size()));
        //random.click();
        product.click();
        ProductPage appleCinemaPage= PageFactory.initElements(webDriver, ProductPage.class);
        return appleCinemaPage;
    }

}
