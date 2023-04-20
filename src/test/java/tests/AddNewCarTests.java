package tests;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {

    @BeforeClass
    public void preCondition()
    {
        if(!app.getHelperUser().isLogged())
        {
            app.getHelperUser().login(new User().setEmail("cherkasskyantony@gmail.com").setPassword("Ant1990$"));
            logger.info("Before method finish logout");
        }

    }
    @Test
    public void addNewCarSuccessAll()
    {
        logger.info("Start test 'addNewCarSuccessAll'");
        logger.info("Test data ---> New Car: 'Mazda' & Model: 'M3' ");
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-" + i)
                .price(50)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+" "+car.getModel()+" added successful");
        logger.info("'Assert check is Car 'added successful' massage exist'");
    }

    @Test
    public void addNewCarSuccess()
    {
        logger.info("Test data ---> New Car: 'BMW' & Model: 'X5' ");
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("BMW")
                .model("X5")
                .year("2020")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("854-935-" + i)
                .price(50)
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+" "+car.getModel()+" added successful");
        logger.info("'Assert check is Car 'added successful' massage exist'");
    }


    @AfterMethod
    public void posCondition(){
        app.getHelperCar().returntoHome();
    }
}








