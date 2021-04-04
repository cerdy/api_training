package fr.esiea.ex4A.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private final UserRepository user = new UserRepository();
    @Test void addUser() {
        user.addUser(new UserData("mail@gmail.com", "username", "userTweeter", "FR", "M", "F"));
        assertEquals(5, user.userDataList.size());
        assertEquals("FR", user.userDataList.get(4).userCountry);
    }

    @Test void getUserFromUserName() {
        assertNull(user.getUserFromUserName("toto"));
        assertNotNull(user.getUserFromUserName("pierre"));
    }
}
