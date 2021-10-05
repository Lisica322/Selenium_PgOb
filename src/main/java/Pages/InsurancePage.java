package Pages;

import managers.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class InsurancePage extends BasePage {

    @FindBy(xpath = "//span[@itemprop='item']")
    private WebElement title;


    @FindBy(xpath = "//*[text()='Оформить онлайн']/../../a[@data-test-id]")
    private WebElement buttonCheckoutOnline;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public InsurancePage checkOpenInsurancePage(String checkName) {
        Assertions.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                checkName,
                title.getText());
        return this;
    }

    public BaseProductPage checkoutOnline() {
        DriverManager.getDriverManager().getDriver().navigate().back();
//        waitUtilElementToBeClickable(buttonCheckoutOnline).click();
        return pageManager.getBaseProductPage().checkOpenInsurancePage();
    }
}
