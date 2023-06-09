package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));

    }

    public void fillCarForm(Car car)
    {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.xpath("//input[@id='year']"),car.getYear());
        select(By.id("fuel"),car.getFuel());
        type(By.id("seats"),String.valueOf(car.getSeats()));
        type(By.id("class"),car.getCarClass());
        type(By.id("serialNumber"),car.getCarRegNumber());
        type(By.id("price"),String.valueOf(car.getPrice()));
        //type(By.id("price"),car.getPrice()+"");
        type(By.id("about"),car.getAbout());


    }

    private void select(By locator, String option)
    {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //Gas
        //select.selectByIndex(5);
        //select.selectByValue("Gas");
        //select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location)
    {
        type(By.id("pickUpPlace"),location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returntoHome()
    {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo)
    {
        typeCity(city);
        click(By.id("dates"));
        //"4/25/2023","4/28/2023"


        String[] parts = dateFrom.split("/");
        String dayFrom = parts[1];

        String locatorFrom = "//div[text()=' "+dayFrom+" ']";
        click(By.xpath(locatorFrom));

        String[] parts1 = dateTo.split("/");
        String dayTo = parts1[1];

        String locatorTo = "//div[text()=' "+dayTo+" ']";
        click(By.xpath(locatorTo));
    }

    private void typeCity(String city)
    {
        type(By.id("city"),city);
        click(By.cssSelector("div.pac-item"));
    }
}
