package com.incredibly_humble.app.tests;

import com.incredibly_humble.models.User;

import static com.incredibly_humble.models.User.AccountType.USER;
import static com.incredibly_humble.models.User.AccountType.MANAGER;
import static org.junit.Assert.assertEquals;

import org.junit.*;


/**
 * Test class for equals method in User
 */
public class UserTest {
    User test = new User("Derek", "test@gatech.edu", "password", USER);
    User test2 = new User("Derek", "test2@gatech.edu", "password", USER);
    User test3 = new User("Derek", "test@gatech.edu", "Password", USER);
    User test4 = new User("Test", "test@gatech.edu", "password", MANAGER);
    String test5 = "Test";

    @Test
    public void testEquals() {
        assertEquals("Different emails should not be equal", false, test.equals(test2));
        assertEquals("Different passwords should not be equal", false, test.equals(test3));
        assertEquals("Only email address and name should be compared", true, test.equals(test4));
        assertEquals("Instances of other objects should not cause an exception", false, test.equals(test5));
    }
}
