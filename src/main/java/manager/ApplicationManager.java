package manager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ApplicationManager
{
    WebDriver wd;
    HelperUser helperUser;


    public void init()
    {
        ChromeOptions options = new ChromeOptions();
        ChromeOptions chromeOptions = options.addArguments("--remote-allow-origins=*");
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.web.app/search");
        helperUser = new HelperUser(wd);

    }

    public HelperUser getHelperUser()
    {
        return helperUser;
    }

    public void stop()
    {
        //wd.quit();
    }



}
