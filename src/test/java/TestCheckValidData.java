import org.junit.Assert;
import org.junit.Test;
import service.CheckValidUserData;
import domain.User;

public class TestCheckValidData {


    private User user = new User("", "", "");

    private CheckValidUserData checkValidUserData = new CheckValidUserData(user);

    @Test
    public void testCheckValidUserData() {
        boolean check = checkValidUserData.isValidDAta();
        Assert.assertEquals(false, check);
    }

}
