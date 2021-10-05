package test;

import basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;

public class FirstTest extends BaseTests {

    @Test
    public void startTest() {

        app.getHomePage()
                .closeCitiesDialog()
                .selectBaseMenu("Бытовая техника")
                .selectSubMenu("Стирка и сушка");


    }

}
