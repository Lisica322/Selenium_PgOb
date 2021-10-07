package managers;

import Pages.*;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class PageManager {

    ProductListPage productListPage;

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка проверки открытия
     */
    private ProductInfoPage productInfoPage;

    /**
     * Страничка таблеток
     */
    /**
     * Страничка корзины
     */
    private BasketPage basketPage;

    private InsurancePage insurancePage;


    private PageManager() {
    }

    public ProductInfoPage getProductInfoPage() {
        if (productInfoPage == null) {
            productInfoPage = new ProductInfoPage();
        }
        return productInfoPage;
    }

    public ProductListPage getProductListPage() {
        if (productListPage == null) {
            productListPage = new ProductListPage();
        }
        return productListPage;
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


    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }


    public InsurancePage getInsurancePage() {
        if (insurancePage == null) {
            insurancePage = new InsurancePage();
        }
        return insurancePage;
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }
}