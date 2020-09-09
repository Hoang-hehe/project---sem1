package com.test.app;

import static org.junit.Assert.assertTrue;

import com.test.app.bl.Login_user;

import org.junit.Test;

public class LoginTest {

    @Test
    public void CheckIDTC01() {
        Login_user lg = new Login_user();
        int result = lg.first_log("");
        int expected = 1;
        assertTrue(result == expected);
    }

    @Test
    public void CheckIDTC02() {
        Login_user lg = new Login_user();
        int result = lg.first_log(null);
        int expected = 1;
        assertTrue(result == expected);
    }

    @Test
    public void CheckIDTC03() {
        Login_user lg = new Login_user();
        int result = lg.first_log("1231233");
        int expected = 1;
        assertTrue("exist", result == expected);
    }

    @Test
    public void CheckIDTC04() {
        Login_user lg = new Login_user();
        int result = lg.first_log("001201018172");
        int expected = 1;
        assertTrue("not exist", result == expected);
    }

    @Test
    public void checkPassTC05() throws InterruptedException {
        Login_user lg = new Login_user();
        boolean result = lg.session_login("1", "654123");
        boolean expected = true;
        assertTrue("not exist", result == expected);
    }
    @Test
    public void checkPassTC06() throws InterruptedException {
        Login_user lg = new Login_user();
        boolean result = lg.session_login("1", "123456");
        boolean expected = true;
        assertTrue("not exist", result == expected);
    }

}
