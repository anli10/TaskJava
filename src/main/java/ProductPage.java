import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage {


    @FindBy(xpath=".//*[@id='input-option218']/div[1]/label/input")
    private WebElement radio;

    @FindBy(xpath=".//*[@id='input-option223']/div[1]/label/input")
    private WebElement checkBox;

    @FindBy(xpath=".//*[@id='input-option208']")
    private WebElement text;

    @FindBy(id="input-option226")
    private WebElement select;

    @FindBy(id="button-cart")
    private WebElement addButton;

    @FindBy(id="cart")
    private WebElement cartButton;

    @FindBy(xpath=".//*[@id='cart']/ul/li[1]/table/tbody/tr/td[3]")
    private WebElement quantity;
    @FindBy(xpath=".//*[@id='cart']/ul/li/p")
    private WebElement message;

    private WebDriver webDriver;


    public ProductPage(WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public String getQuantity() {
        try {
            message.isDisplayed();
            return "x 0" ;
        }
         catch (NoSuchElementException e){
            return  quantity.getText();
         }
    }

    public void addToCart(){


        List<WebElement> options = select.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if(option.getText().contains("Red"))
                option.click();
        }
        addButton.click();
        cartButton.click();
    }
}
