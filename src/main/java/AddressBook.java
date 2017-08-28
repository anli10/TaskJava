import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddressBook {


    @FindBy(xpath=".//*[@id='content']/div[1]/table/tbody/tr/td[1]")
    private WebElement addressInformation;
    @FindBy(xpath=".//*[@id='content']/div[1]/table/tbody/tr/td[1]")
    private List<WebElement> addressBook;

    private WebDriver webDriver;

    public WebElement getAddressInformation() {
        return addressInformation;
    }

    public AddressBook(WebDriver webDriver){
        this.webDriver=webDriver;
    }
    public boolean check(String em){
        int n=addressBook.size();
        if(addressBook.get(n-1).getText().contains(em))return true;
        return false;
    }
}
