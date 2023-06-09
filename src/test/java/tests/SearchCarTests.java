package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase
{
    @Test
    public void searchCurrentMonthSuccess()
    {
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel","4/21/2023","4/28/2023");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared(By.className("car-card")));
    }
}
