package com.test.app.bl;

import java.sql.SQLException;

import com.test.app.dal.Database;

public class profile extends General {
    public void log() throws SQLException {
        Database db = new Database();
        db.getInfoByID(getID_login_now());
        // int ID = getID_login_now();
        // System.out.println(ID);
        // System.out.println("vcl");
        
        
    }
}