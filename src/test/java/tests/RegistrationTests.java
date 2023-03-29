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
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());
        int z =(int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .withEmail("snow"+i+"@gmail.com")
                .withPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    }
    @Test
    public void registrationWrongPassword()
    {
        User user = new User()
                .withFirstName("Anton")
                .withLastName("Cherkassky")
                .withEmail("cherkasskyantony@gmail.com")
                .withPassword("1");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");

    }
    @Test
    public void registrationWrongEmail()
    {
        User user = new User()
                .withFirstName("Anton")
                .withLastName("Cherkassky")
                .withEmail("cherkasskyantonygmail.com")
                .withPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Wrong email format\n" +
                "Wrong email format");

    }
    @Test
    public void registrationWithoutFirstName()
    {
        User user = new User()
                .withFirstName("")
                .withLastName("Cherkassky")
                .withEmail("cherkasskyantony@gmail.com")
                .withPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");

    }

    @Test
    public void registrationWithoutLastName()
    {
        User user = new User()
                .withFirstName("Antony")
                .withLastName("")
                .withEmail("cherkasskyantony@gmail.com")
                .withPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");

    }

    @Test
    public void registrationWithoutEmail()
    {
        User user = new User()
                .withFirstName("Antony")
                .withLastName("Cherkassky")
                .withEmail("")
                .withPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Email is required");

    }
    @Test
    public void registrationWithoutPassword()
    {
        User user = new User()
                .withFirstName("Antony")
                .withLastName("Cherkassky")
                .withEmail("cherkasskyantony@gmail.com")
                .withPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");

    }
    @Test
    public void registrationRegisteredUser()
    {
        User user = new User()
                .withFirstName("Antony")
                .withLastName("Cherkassky")
                .withEmail("cherkasskyantony@gmail.com")
                .withPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"User already exists\"");

    }
    @Test
    public void registrationWithoutFillingInRequiredFields()
    {
        User user = new User()
                .withFirstName("")
                .withLastName("")
                .withEmail("")
                .withPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");

    }

    @Test
    public void registrationWithoutClickOnTermsOfUseButton()
    {
        User user = new User()
                .withFirstName("Anton")
                .withLastName("Cherkassky")
                .withEmail("cherkassky@antonygmail.com")
                .withPassword("Ant1990$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        //app.getHelperUser().checkPolicyXY();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        app.getHelperUser().submit();

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}

