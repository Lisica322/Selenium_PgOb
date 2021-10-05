package managers;

import Pages.*;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка "Посудомоечная машина DEXP M6C7PD белый"
     */
    private DishwasherPage dishwasherPage;

    /**
     * Страничка меню таблеток для посудомоечной машины
     */
    private DishwasherTabletsMenuPage dishwasherTabletsMenuPage;

    /**
     * Страничка таблеток
     */
    private DishwasherTabletPage dishwasherTabletPage;
    /**
     * Страничка корзины
     */
    private BasketPage basketPage;

    private InsurancePage insurancePage;

    private BaseProductPage baseProductPage;

    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */



    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }


    public DishwasherPage getDishwasherPage() {
        if (dishwasherPage == null){
            dishwasherPage = new DishwasherPage();
        }
        return dishwasherPage;
    }

    public DishwasherTabletsMenuPage getDishwasherTabletsMenuPage() {
        if (dishwasherTabletsMenuPage == null){
            dishwasherTabletsMenuPage = new DishwasherTabletsMenuPage();
        }
        return dishwasherTabletsMenuPage;
    }

    public DishwasherTabletPage getDishwasherTabletPage() {
        if (dishwasherTabletPage == null){
            dishwasherTabletPage = new DishwasherTabletPage();
        }
        return dishwasherTabletPage;
    }

    public BasketPage getBasketPage() {
        if (basketPage == null){
            basketPage = new BasketPage();
        }
        return basketPage;
    }

    public BaseProductPage getBaseProductPage() {
        if (baseProductPage == null){
            baseProductPage = new BaseProductPage();
        }
        return baseProductPage;
    }
    public InsurancePage getInsurancePage() {
        if (insurancePage == null) {
            insurancePage = new InsurancePage();
        }
        return insurancePage;
    }
}