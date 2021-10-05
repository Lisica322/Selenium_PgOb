package basetestsclass;

import managers.DriverManager;
import managers.InitManager;
import managers.PageManager;
import managers.TestPropManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static util.Const.BASE_URL;


public class BaseTests {

    protected PageManager app = PageManager.getPageManager();
    /**
     * Менеджер WebDriver
     *
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
      //  DriverManager.setChromeOptions();
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
