package Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductListPage extends BasePage {

    public String product;
    @FindBy(xpath = "//a[contains(@class,'name')]/span")
    private WebElement buttonDishwasher;
    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@class = 'products-list__content']")
    private List<WebElement> menuListAppliances;
    @FindBy(xpath = "//div[@class = 'products-list__content']")
    private List<WebElement> menuSubListAppliances;

    public ProductListPage clickBuyButton(String SubProduct) {
        for (WebElement menuItem : menuListAppliances) {
            WebElement title = menuItem.findElement(By.xpath("//a[contains(@class,'name')]/span"));
            if (menuItem.getText()./*trim().equalsIgnoreCase*/contains(SubProduct)) {
                waitUtilElementToBeClickable(menuItem).findElement(By.xpath("//button[contains(@class, 'buy')]")).click();
                product = menuItem.findElement(By.xpath("")).getText();

                return this;
            }
        }
        Assertions.fail("Продукт '" + SubProduct + "' не был найден на странице!");
        return this;
    }

    public ProductListPage checkOpenPage(String nameProduct) {
        Assertions.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                nameProduct,
                title.getText());
        return this;
    }

    public ProductInfoPage selectProductByName(String nameProduct) {
        for (WebElement menuItem : menuListAppliances) {
            if (menuItem.getText().equalsIgnoreCase(nameProduct)) {
                title.click();
                return pageManager.getProductInfoPage().checkOpenProductPage(nameProduct);
            }
        }
        Assertions.fail("Продукт '" + nameProduct + "' не было найдено на стартовой странице!");
        return pageManager.getProductInfoPage().checkOpenProductPage(nameProduct);
    }
}