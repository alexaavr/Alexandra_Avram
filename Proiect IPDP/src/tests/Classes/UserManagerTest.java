package Classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTest {

    UserManager um = new UserManager();
    Item it = new Item("Something");
    Item itCode = new Item(1200);

    @Test
    public void getString() {
        assertEquals(UserManager.getString(it), it.toString());
    }

    @Test
    public void findItembyCode() {
        assertEquals(um.findItem(itCode), false);
    }
}