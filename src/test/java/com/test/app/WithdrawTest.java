package com.test.app;

import static org.junit.Assert.assertTrue;

import com.test.app.bl.General;
import com.test.app.bl.Withdraw;

import org.junit.Test;

public class WithdrawTest {

    @Test
    public void WithdrawTC09(){
        Withdraw wd = new Withdraw();
        General general = new General(5);
        long money = 10000000; //số tiền nhập quá số tiền cho phép
        int result =  wd.run(money);
        int expected = 1;
        assertTrue("not exist", result == expected);
    }
    @Test
    public void WithdrawTC10(){
        Withdraw wd = new Withdraw();
        General general = new General(5);
        long money = 100000; //số tiền nhập hợp lệ 
        int result =  wd.run(money);
        int expected = 1;
        assertTrue("not exist", result == expected);
    }

    @Test
    public void WithdrawTC11(){
        Withdraw wd = new Withdraw();
        General general = new General(5);
        long money = -500000; 
        int result =  wd.run(money);
        int expected = 1;
        assertTrue("not exist", result == expected);
    }
    @Test
    public void WithdrawTC12(){
        Withdraw wd = new Withdraw();
        General general = new General(5);
        long money = (Long) null; //null
        int result =  wd.run(money);
        int expected = 1;
        assertTrue("not exist", result == expected);
    }
    
}
