package Pages;

import data.Product;
import managers.DriverManager;
import managers.PageManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {


    protected final DriverManager driverManager = DriverManager.getDriverManager();

    protected PageManager pageManager = PageManager.getPageManager();

    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected static ArrayList<Product> products = new ArrayList<>();
    protected static ArrayList<Product> deletedProducts = new ArrayList<>();

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean waitUtilElementNotToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }
    protected boolean waitUtilTextToBePresent(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
    }
    protected boolean waitUtilElementToBeVisibleInList(List<WebElement> list, String text) {
        for (WebElement element : list)
            if (waitUtilTextToBePresent(element, text))
                return true;

        return false;
    }
}