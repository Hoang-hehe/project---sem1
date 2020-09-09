package com.test.app;

import static org.junit.Assert.assertTrue;

import com.test.app.bl.General;
import com.test.app.bl.Transfer;

import org.junit.Test;

public class TransferTest {

    @Test
    public void TransferTC07(){
        //số tiền hợp lệ, id chuẩn
        General general = new General(1);
        Transfer trs = new Transfer();
        long money = 600000; 
        int result =  trs.run("001201018172", money);
        int expected = 1;
        assertTrue("not exist", result == expected);
    }
    @Test
    public void TransferTC08(){
        //số tiền không hợp lệ, id chuẩn
        General general = new General(1);
        Transfer trs = new Transfer();
        long money = -500000; 
        int result =  trs.run("001201018172", money);
        int expected = 1;
        assertTrue("not exist", result == expected);
    }

    
}
