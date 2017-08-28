import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    @FindBy(id="input-email")
    private WebElement email;

    @FindBy(id="input-password")
    private WebElement password;

    @FindBy(xpath=".//*[@id='content']/div/div[2]/div/form/input")
    private WebElement LoginButton;

    @FindBy(xpath=".//*[@id='column-right']/div/a[4]")
    private WebElement addressBookButton;

    private WebDriver webDriver;

    public LogInPage(WebDriver webDriver){
        this.webDriver=webDriver;
    }
    public void fillCredentials(String em,String pas){
        email.sendKeys(em);
        password.sendKeys(pas);
        LoginButton.click();
    }
    public AddressBook goToAddressPage(){

        addressBookButton.click();
        AddressBook addressBook= PageFactory.initElements(webDriver,AddressBook.class);
        return addressBook;
    }
}
