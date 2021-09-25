import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import user.ChangeUserInfo;
import user.User;

public class TestChangeUserData {

    private User user = new User("Kirill", "1234567891011", "Peterhoff");

    private ChangeUserInfo changeUserInfo = new ChangeUserInfo(user);

    @Test
    public void testChangeName() {
        changeUserInfo.changeUserName("Not Kirill");
        String newName = user.getName();
        Assert.assertEquals("Not Kirill", newName);
    }

    @Test
    public void testChangeAge() {
        changeUserInfo.changeUserPhone("1234567");
        String newPhone = user.getPhoneNumber();
        Assert.assertEquals("1234567", newPhone);
    }

    @Test
    public void testChangeAddress() {
        changeUserInfo.changeUserAddress("Saint-Petesburg");
        String newAddress = user.getAddress();
        Assert.assertEquals("Saint-Petesburg", newAddress);
    }

}
