package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase
{
    public HelperUser(WebDriver wd)
    {
        super(wd);
    }

    public void openLoginForm()
    {
        click(By.xpath("//a[@class='navigation-link'][normalize-space()='Log in']"));
    }

    public void fillLoginForm(String email,String password)
    {
        type(By.xpath("//input[@id='email']"),password);
        type(By.name("password"), password);
    }

    public void submitLogin()
    {
        click(By.xpath("//button[contains(text(),'Y’alla!')]"));
    }

}
