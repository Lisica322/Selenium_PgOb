package Pages;

import data.Product;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.function.BooleanSupplier;


public class BasketPage extends BasePage {

    @FindBy(xpath = "//div[@class='total-amount__label']//span[@class='price__current']")
    private WebElement cartSumElement;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement upperCartSumElement;

    @FindBy(xpath = "//div[contains(@class, 'product')]//span[@class='price__current']")
    private List<WebElement> listPrices;

    @FindBy(xpath = "//span[contains(@class,'radio-button') ]")
    private List<WebElement> warrantyElements;

    @FindBy(xpath = "  //span[contains(@class, 'row__price')]")
    private List<WebElement> warrantyPriceElements;

    @FindBy(xpath = "//a[contains(@class, 'product-name')]")
    private List<WebElement> listNames;

    @FindBy(xpath = "//div[contains(@class,'header')]//span[@class = 'restore-last-removed']")
    private WebElement restore;

    @FindBy(xpath = "//span[@class='cart-link__badge']")
    private WebElement numberInCart;

    public BasketPage() {
    }

    /**
     * Проверка соответстия суммы в корзине
     *
     * @return CartPage
     */
    public BasketPage checkSum() {
        double cartSum = Double.parseDouble(cartSumElement.getText().split("Р")[0].replaceAll("\\s+", ""));
        double upperCartSum = Double.parseDouble(upperCartSumElement.getText().replaceAll("[^0-9]", ""));
        double productsSum = 0;
        for (Product product : products)
            productsSum += product.getPrice() * product.getQuantity();
        Assertions.assertFalse(cartSum != productsSum || upperCartSum != productsSum, "Сумма в корзине не соответствует сумме покупок");
        return this;
    }

    /**
     * Удаление стандартной скидки в корзине
     *
     */

    /**
     * Проверка соответсвия гарантии и суммы
     *
     * @return CartPage
     */
    public BasketPage checkGuarantyAndPrice() {
        double price;
        for (Product product : products) {
            price = Double.parseDouble(listPrices.get(products.indexOf(product)).getText().replaceAll("[^0-9]", ""));
            if (product.getWarranty()) {
                switch (product.getYearsGuaranty()) {
                    case 0:
                        Assertions.assertEquals(warrantyElements.get(products.indexOf(product)).getText(), "Без доп. гарантии", "Меню гарантии не совпадает");
                        break;
                    case 1:
                        Assertions.assertEquals(warrantyElements.get(products.indexOf(product)).getText(), "+ 12 мес.", "Меню гарантии не совпадает");
                        price += Double.parseDouble(warrantyPriceElements.get(products.indexOf(product)).getText().replaceAll("[^0-9]", ""));
                        break;
                    case 2:
                        Assertions.assertEquals(warrantyElements.get(products.indexOf(product)).getText(), "+ 24 мес.", "Меню гарантии не совпадает");
                        price += Double.parseDouble(warrantyPriceElements.get(products.indexOf(product)).getText().replaceAll("[^0-9]", ""));
                        break;


                }
                Assertions.assertEquals(price, product.getPrice(), "Цена продукта не совпадает");
            }
        }
        return this;
    }

    /**
     * Удаление товара из корзины
     *
     * @param name - имя заданное в поиске
     * @return CartPage
     */
    public BasketPage delete(String name) {
        boolean isSuccess = false;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                deletedProducts.add(product);
                for (WebElement element : listNames) {
                    if (element.getText().contains(name)) {
                        WebElement deleteButton = element.findElement(By.xpath("./../..//button[contains(.,'Удалить')]"));
                        Assertions.assertTrue((BooleanSupplier) waitUtilElementToBeClickable(deleteButton), "Кнопка удалить не активна");
                        deleteButton.click();
                        Assertions.assertFalse(waitUtilElementNotToBeVisible(element), "Элемент не удален");
                        products.remove(product);
                        isSuccess = true;
                        break;
                    }
                }
                Assertions.assertTrue(isSuccess, "Элемент для удаления не найден");
                break;
            }
        }
        Assertions.assertTrue(isSuccess, "Элемента с таким именем нет в массиве");
        checkSum();
        return this;
    }

    /**
     * Добавление существующего товара
     *
     * @param name - имя заданное в поиске
     * @return CartPage
     */
    public BasketPage add(String name) {
        boolean isSuccess = false;
        for (WebElement element : listNames) {
            if (element.getText().contains(name)) {
                WebElement addButton = element.findElement(By.xpath("./../../../../..//i[@class='count-buttons__icon-plus']"));
                        Assertions.assertTrue((BooleanSupplier) waitUtilElementToBeClickable(addButton), "Кнопка добавить не активна");
                int prevNumber = Integer.parseInt(numberInCart.getText());
                addButton.click();
                Assertions.assertTrue(waitUtilTextToBePresent(numberInCart, String.valueOf(prevNumber + 1)), "Не верное изменение счетчика корзины");
                isSuccess = true;
                break;
            }
        }
        Assertions.assertTrue(isSuccess, "Не добавился +1 товар");
        isSuccess = false;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                product.setQuantity(product.getQuantity() + 1);
                isSuccess = true;
                break;
            }
        }
        Assertions.assertTrue(isSuccess, "Не найден товар в массиве");
        return this;

    }

    /**
     * Возвращение удаленного ранее товара
     *
     * @return CartPage
     */
    public BasketPage addBack() {
        Assertions.assertTrue((BooleanSupplier) waitUtilElementToBeClickable(restore), "Кнопка восстановить не кликабельна");
        int prevNumber = Integer.parseInt(numberInCart.getText());
        restore.click();
        Product lastDeleted = deletedProducts.get(deletedProducts.size() - 1);
        Assertions.assertTrue(waitUtilTextToBePresent(numberInCart, String.valueOf(prevNumber + lastDeleted.getQuantity())), "Не верное изменение счетчика корзины");
        Assertions.assertTrue(waitUtilElementToBeVisibleInList(listNames, lastDeleted.getName()));
        products.add(lastDeleted.getIndexInArray(), lastDeleted);
        checkSum();
        return this;
    }

}