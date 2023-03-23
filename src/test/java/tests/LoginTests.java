package tests;
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
    public void loginSuccess()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990$");
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().closeWindow();

    }

    @Test
    public void loginSuccessModel()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990$");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        app.getHelperUser().closeWindow();
    }

    @Test
    public void loginWrongEmail()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantonygmail.com","Ant1990$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isElementPresent(By.cssSelector("div[class='ng-star-inserted']")));


    }


    @Test
    public void loginWrongPassword()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkassky@antonygmail.com","Ant1990");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.cssSelector(".message")));
        app.getHelperUser().closeWindow();


    }

    @AfterMethod
    public void postCondition()
    {
        //app.getHelperUser().closeWindow();
    }


}
