import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    @FindBy(id="input-firstname")
    private WebElement firstName;

    @FindBy(id="input-lastname")
    private WebElement lastName;

    @FindBy(id="input-email")
    private WebElement email;

    @FindBy(id="input-telephone")
    private WebElement telephone;

    @FindBy(id="input-password")
    private WebElement password;

    @FindBy(id="input-confirm")
    private WebElement confirm;

    @FindBy(xpath=".//*[@id='content']/form/div/div/input[1]")
    private WebElement readTerms;

    @FindBy(xpath=".//*[@id='content']/form/div/div/input[2]")
    private WebElement submit;

    @FindBy(xpath=".//*[@id='column-right']/div/a[2]")
    private WebElement editInformationButton;

    private WebDriver webDriver;

    public RegisterPage(WebDriver webDriver){
        this.webDriver=webDriver;
    }
    public void fillInformation(String first,String last,String e,String t,String pass,String conf){

        firstName.sendKeys(first);
        lastName.sendKeys(last);
        email.sendKeys(e);
        telephone.sendKeys(t);
        password.sendKeys(pass);
        confirm.sendKeys(conf);

    }
    public void submitForm(){
        readTerms.click();
        submit.click();
    }
    public EditInformation goToEditInformation(){
        editInformationButton.click();
        EditInformation editPage= PageFactory.initElements(webDriver,EditInformation.class);
        return editPage;
    }

}
