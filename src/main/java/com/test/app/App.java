package com.test.app;

import java.io.IOException;
import java.sql.SQLException;

import com.test.app.dal.Database;
import com.test.app.gui.mainMenu;

public class App {
	public static void main(String[] args) throws SQLException, InterruptedException, IOException {
		mainMenu.first_login();
		// Database db = new Database();
		// db.getTrade_date_recent(1);


	}

}
