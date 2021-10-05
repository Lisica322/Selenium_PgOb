package Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{
    //менюшка
    @FindBy(xpath = "//div[@class = 'menu-desktop__root']")
    private List<WebElement> menuList;
    //менюшка БТ
    @FindBy(xpath = "//a[@class = 'popular-categories__title']")
    private List<WebElement> menuListAppliances;

    @FindBy(xpath = "//*[@class='btn btn-additional']")
    private WebElement cityButton;


    public HomePage closeCitiesDialog() {
        waitUtilElementToBeClickable(cityButton).click();
        return this;
    }


    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : menuList) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }
        public InsurancePage selectSubMenu(String nameSubMenu) {
            for (WebElement menuItem : menuListAppliances) {
                if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                    waitUtilElementToBeClickable(menuItem).click();
                    return pageManager.getInsurancePage().checkOpenInsurancePage(nameSubMenu);
                }
            }
            Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
            return pageManager.getInsurancePage();
        }
  }
//    public void clickSearch() {
//        waitUtilElementToBeClickable(searchBar).click();
//        waitUtilElementToBeClickable(searchBarOk).click();
//    }



