import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditInformation {


    @FindBy(id="input-firstname")
    private WebElement firstName;

    @FindBy(id="input-lastname")
    private WebElement lastName;

    @FindBy(id="input-email")
    private WebElement email;

    @FindBy(id="input-telephone")
    private WebElement telephone;

    private WebDriver webDriver;

    public EditInformation(WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getTelephone() {
        return telephone;
    }
}
