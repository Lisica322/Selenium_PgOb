package Pages;

import data.DataManager;
import data.Product;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Utils;
import util.Warranty;

import java.util.List;
import java.util.function.BooleanSupplier;

public class ProductInfoPage extends BasePage {

    DataManager dataManager;
    Product product;

    @FindBy(xpath = "//input[@type ='search' and not (@id='presearch_1q3rti')]")
    private WebElement searchInputField;

    public int warrantyPrice;
    @FindBy(xpath = "//label[contains(@class, 'product-warranty')]")
    List<WebElement> warrantyList;

    @FindBy(xpath = "//span[contains(@class, 'product-warranty__price')]")
    List<WebElement> warrantyPriceList;

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[contains(text(), 'Гарантия') and contains(@class, 'sales')]")
    private WebElement warrantyProduct;

    @FindBy(xpath = "//div[@class = 'cart-products-count']")
    private WebElement basketProductsCount;

    @FindBy(xpath = "//div[@class='product-card-top__buy']//button[@class='button-ui buy-btn button-ui_brand button-ui_passive']")
    private WebElement buyButton;

    @FindBy(xpath = "//span[@class='cart-link__lbl']")
    private WebElement basketButton;

    public ProductInfoPage checkOpenProductPage(String checkName) {
        Assertions.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                checkName,
                title.getText());
        return this;
    }

    public ProductInfoPage chooseWarranty(Warranty warranty) {
        switch (warranty) {
            case THIRTY_SIX_MONTH:
                warrantyProduct.click();
                warrantyList.get(1).click();
                warrantyPrice = Utils.convertPrices(warrantyPriceList.get(2).getText());
                product = dataManager.getProductByName(title.getText());
                product.setWarrantyPrice(warrantyPrice);

            case TWENTY_FOUR_MONTH:
                warrantyProduct.click();
                warrantyList.get(0).click();
                warrantyPrice = Utils.convertPrices(warrantyPriceList.get(2).getText());
                product = dataManager.getProductByName(title.getText());
                product.setWarrantyPrice(warrantyPrice);

            case FOURTY_EIGHT_MONTH:
                warrantyProduct.click();
                warrantyList.get(2).click();
                warrantyPrice = Utils.convertPrices(warrantyPriceList.get(2).getText());
                product = dataManager.getProductByName(title.getText());
                product.setWarrantyPrice(warrantyPrice);

        }
        return this;
    }
//добавление в корзину
    public ProductInfoPage addToBasket() {
        int productsNumber;
        try {
            productsNumber = Integer.parseInt(basketProductsCount.getText());
        } catch (NoSuchElementException ex) {
            System.out.println(productsNumber = 0);
        }
        Assertions.assertTrue((BooleanSupplier) waitUtilElementToBeClickable(buyButton), "Кнопка добавить в корзину не кликабельна");
        buyButton.click();
        Assertions.assertTrue(waitUtilTextToBePresent(basketProductsCount, String.valueOf(productsNumber + 1)), "Не верное изменение счетчика корзины");
        return this;
    }
    public ProductInfoPage searchForProduct(String name) {
        Assertions.assertTrue((BooleanSupplier) waitUtilElementToBeClickable(searchInputField), "Поисковая строка не кликабельна");
        searchInputField.click();
        searchInputField.clear();
        searchInputField.sendKeys(name);
        Assertions.assertEquals(searchInputField.getAttribute("value"),name,"Введенный текст не совпадает");
        searchInputField.sendKeys(Keys.ENTER);
        return pageManager.getProductInfoPage();
    }
    /**
     * Переход в корзину
     */
    public BasketPage goToBasket()  {
        Assertions.assertTrue((BooleanSupplier) waitUtilElementToBeClickable(basketButton), "Кнопка корзины не кликабельна");
        basketButton.click();
        pageManager.getBasketPage()
                .checkSum()
                .checkGuarantyAndPrice();

        return pageManager.getBasketPage();
    }
}
