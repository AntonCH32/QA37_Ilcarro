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
        }
    }

    @Test
    public void loginSuccess1()
    {
        User user = new User().withEmail("cherkasskyantony@gmail.com").withPassword("Ant1990$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().closeWindow();
    }


    @Test
    public void loginSuccess()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().closeWindow();
    }


    @Test
    public void loginSuccessModel()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test
    public void loginWrongEmail()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantonygmail.com","Ant1990$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");

    }

    @Test
    public void loginWrongPassword()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990");
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.cssSelector(".message")));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().closeWindow();


    }

    @Test
    public void loginUnregistered()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("luck@gmail.com","luck12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
    }


    @AfterMethod
    public void postCondition()
    {
        app.getHelperUser().closeWindow();
    }


}
