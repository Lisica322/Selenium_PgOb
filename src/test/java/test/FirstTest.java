package test;

import basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;
import util.Warranty;

public class FirstTest extends BaseTests {

    @Test
    public void startTest() {
        app.getHomePage()
                .closeCitiesDialog()
                .selectBaseMenu("Бытовая техника")
                .selectSubMenu("Техника для дома")
                .clickOnSubCategoryMenu("Стиральные машины")
                .selectProductByName("Стиральная машина DEXP WM-F610NMA/WW")
                .chooseWarranty(Warranty.TWENTY_FOUR_MONTH)
                .addToBasket()
                .searchForProduct("Таблетки для ПММ Filtero 704")
                .addToBasket()
                .goToBasket()
                .delete("Таблетки для ПММ Filtero 704")
                .add("Стиральная машина DEXP WM-F610NMA/WW")
                .add("Стиральная машина DEXP WM-F610NMA/WW")
                .addBack();
                //.checkOpenPage("Стиральные машины");



    }
//функция добавления в корзину
}
