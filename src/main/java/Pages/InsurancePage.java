package Pages;

import managers.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class InsurancePage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement title;



    @FindBy(xpath = "//h1")
    private WebElement subtitle;

    @FindBy(xpath = "//div[@class = 'subcategory']")
    private List<WebElement> subCategoryMenu;



    public ProductListPage clickOnSubCategoryMenu(String nameProduct) {
        for (WebElement menuItem : subCategoryMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameProduct)) {
                return pageManager.getProductListPage().checkOpenPage(nameProduct);
            }
        }
        Assertions.fail("Продукт '" + nameProduct + "' не было найдено на стартовой странице!");
        return pageManager.getProductListPage().checkOpenPage(nameProduct);
    }

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public InsurancePage checkOpenPage(String nameProduct) {
        Assertions.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                nameProduct,
                title.getText());
        return this;
    }

    public ProductInfoPage openPreviousPage(String nameProduct) {
        DriverManager.getDriverManager().getDriver().navigate().back();
        return pageManager.getProductInfoPage().checkOpenProductPage(nameProduct);
    }
}
