package tests;
import org.testng.annotations.Test;

public class LoginTests extends TestBase
{
    @Test
    public void loginSuccess()
    {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("cherkasskyantony@gmail.com","Ant1990$");
        app.getHelperUser().submitLogin();
    }
}
