package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DishwasherPage {

    @FindBy(xpath = "//h1[@data-test-id]")
    private WebElement title;

    @FindBy(xpath = "//*[text()='Оформить онлайн']/../../a[@data-test-id]")
    private WebElement buttonCheckoutOnline;

}
