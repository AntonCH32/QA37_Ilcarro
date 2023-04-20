package tests;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase
{
    @BeforeMethod
    public void preCondition()
    {
        if(app.getHelperUser().isLogged())
        {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void loginSuccess1()
    {
        logger.info("Start test 'loginSuccess1'");
        logger.info("Test data ---> Email: 'cherkasskyantony@gmail.com' & Password: 'Ant1990$' ");
        User user = new User().setEmail("cherkasskyantony@gmail.com").setPassword("Ant1990$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().closeWindow();
        logger.info("'Assert check is 'Logged in success' massage exist'");
    }


    @Test
    public void loginSuccess()
    {
        logger.info("Test data ---> Email: 'cherkasskyantony@gmail.com' & Password: 'Ant1990$' ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().closeWindow();
        logger.info("'Assert check is 'Logged in success' massage exist'");
    }


    @Test
    public void loginSuccessModel()
    {
        logger.info("Test data ---> Email: 'cherkasskyantony@gmail.com' & Password: 'Ant1990$' ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("'Assert check is 'Logged in success' massage exist'");

    }

    @Test
    public void loginWrongEmail()
    {
        logger.info("Test data ---> Email: 'cherkasskyantonygmail.com' & Password: 'Ant1990$' ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantonygmail.com","Ant1990$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        logger.info("'Assert check is alert present 'It'snot look like email' massage exist'");

    }

    @Test
    public void loginWrongPassword()
    {
        logger.info("Test data ---> Email: 'cherkasskyantonygmail.com' & Password: 'Ant1990' ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990");
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.cssSelector(".message")));
        Assert.assertFalse(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().closeWindow();
        logger.info("'Assert check is 'isYallaButtonNotActive'");


    }

    @Test
    public void loginUnregistered()
    {
        logger.info("Test data ---> Email: 'luck@gmail.com' & Password: 'luck12345$' ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("luck@gmail.com","luck12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
        logger.info("'Assert check is alert present 'Login or Password incorrect'");
    }


    @AfterMethod
    public void postCondition()
    {
        app.getHelperUser().closeWindow();
    }


}
