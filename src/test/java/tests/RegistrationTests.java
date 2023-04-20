package tests;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void registrationSuccess()
    {
        logger.info("Start test 'registrationSuccess'");
        logger.info("Test data ---> New User: 'Lisa' & LastName: 'Snow' ");
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());
        int z =(int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow"+i+"@gmail.com")
                .setPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        // You are logged in success
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("'Assert check is 'You are logged in success' massage exist'");
    }

    @Test
    public void registrationWrongPassword()
    {
        logger.info("Test data ---> New User: 'Anton' & LastName: 'Cherkassky' ");
        User user = new User()
                .setFirstName("Anton")
                .setLastName("Cherkassky")
                .setEmail("cherkasskyantony@gmail.com")
                .setPassword("1");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        logger.info("'Assert check is 'Password must contain minimum 8 symbols' massage exist'");

    }
    @Test
    public void registrationWrongEmail()
    {
        logger.info("Test data ---> New User: 'Anton' & WrongMail: 'cherkasskyantonygmail.com' ");
        User user = new User()
                .setFirstName("Anton")
                .setLastName("Cherkassky")
                .setEmail("cherkasskyantonygmail.com")
                .setPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("'Assert check is 'Wrong email format' massage exist'");

    }
    @Test
    public void registrationWithoutFirstName()
    {
        logger.info("Test data ---> New User: 'Empty Name' & Mail: 'cherkasskyantony@gmail.com' ");
        User user = new User()
                .setFirstName("")
                .setLastName("Cherkassky")
                .setEmail("cherkasskyantony@gmail.com")
                .setPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check is 'Name is required' massage exist");

    }

    @Test(enabled = false)
    public void registrationWithoutLastName()
    {
        logger.info("Test data ---> New User: 'Empty LastName' & Mail: 'cherkasskyantony@gmail.com' ");
        User user = new User()
                .setFirstName("Antony")
                .setLastName("")
                .setEmail("cherkasskyantony@gmail.com")
                .setPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check is 'Last name is required' massage exist");

    }

    @Test
    public void registrationWithoutEmail()
    {
        logger.info("Test data ---> New User: 'Antony' & Mail: 'Empty' ");
        User user = new User()
                .setFirstName("Antony")
                .setLastName("Cherkassky")
                .setEmail("")
                .setPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check is 'Email is required' massage exist");

    }
    @Test
    public void registrationWithoutPassword()
    {
        logger.info("Test data ---> New User: 'Antony' & Password: 'Empty' ");
        User user = new User()
                .setFirstName("Antony")
                .setLastName("Cherkassky")
                .setEmail("cherkasskyantony@gmail.com")
                .setPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check is 'Password is required' massage exist");

    }

    @Test
    public void registrationWithoutFillingInRequiredFields()
    {
        logger.info("Test data ---> New User: 'empty' & RequiredFields: 'Empty' ");
        User user = new User()
                .setFirstName("")
                .setLastName("")
                .setEmail("")
                .setPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
        logger.info("Assert check is 'Name is required' massage exist");

    }


    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}

