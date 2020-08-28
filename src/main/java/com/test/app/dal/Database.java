package com.test.app.dal;

import java.util.Date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Database {

	private Connection conn;
	private PreparedStatement ps;

	public Database() {
		try {
			conn = DriverManager.getConnection(Common.connString);
			// System.out.print("Connect successfully \n");
		} catch (Exception ex) {
			System.out.print("Connection error: " + ex.getMessage());
		}
	}

	public int trade(int userId, int amount, String trade_tye) {// hàm giao dịch
		try {

			ps = conn.prepareCall("{call trade(?,?,?)}");
			ps.setInt(1, userId);
			ps.setInt(2, amount);
			ps.setString(3, trade_tye);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result");
			}
			return -1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public void setAccountType(int userId, int type) {
		try {

			ps = conn.prepareCall("{call setAccountType(?,?)}");
			// truyền giá trị cho các tham sô
			ps.setInt(1, userId);
			ps.setInt(2, type);

			ResultSet rs = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getAccountTypeID(int userId) {
		try {

			ps = conn.prepareCall("{call getAccountTypeID(?)}");
			// truyền giá trị cho các tham sô
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("result");

			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	// hàm tính số dư của tài khoản có id là userId
	public int getBalance(int userId) {
		try {

			ps = conn.prepareCall("{call getBalance(?)}");
			// truyền giá trị cho các tham sô
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("balance");
			}
			return -1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	// hàm lấy lịch sử giao dịch của 1 tài khoản nhất định
	public void GetHistoryTrade(int accId) {
		try {

			ps = conn.prepareCall("{call getHistoryTrade(?)}");
			// truyền giá trị cho các tham sô
			ps.setInt(1, accId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				System.out.println(
						rs.getDate("trade_date") + "|" + rs.getString("type_trade") + "|" + rs.getString("amount"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void getTrade_date_recent(int accId) {
		try {

			ps = conn.prepareCall("{call getTrade_date_recent(?)}");
			// truyền giá trị cho các tham sô
			ps.setInt(1, accId);
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			formatter = new SimpleDateFormat("dd-M-yyyy");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {// nếu có dữ liệu
				System.out.println("Loại giao dịch: " + rs.getString("type_trade") + "\n" + "Số giao dịch: "
						+ rs.getInt("Trade_History_id") + "\n" + "Thời gian giao dịch: " + formatter.format(rs.getDate("trade_date"))
						+ "\n" + "Số tiền giao dịch: " + rs.getInt("amount"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public int InsertAccount(String accountNumber, String password, String name, String phone, int isActive) {
		try {

			ps = conn.prepareCall("{call insertAccount(?,?,?,?,?)}");
			// truyền giá trị cho các tham sô
			ps.setString(1, accountNumber);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, phone);
			ps.setInt(5, isActive);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result"); // insert thành công hay khong nó nằm ở đây
			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	public int UpdateAccount(int id, String accountNumber, String password, String name, String phone, int isActive) {
		try {

			ps = conn.prepareCall("{call updateAccount(?,?,?,?,?,?)}");
			// truyền giá trị cho các tham sô
			ps.setInt(1, id);
			ps.setString(2, accountNumber);
			ps.setString(3, password);
			ps.setString(4, name);
			ps.setString(5, phone);
			ps.setInt(6, isActive);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result"); // insert thành công hay khong nó nằm ở đây
			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	public int login(String accountNumber, String password) {
		try {

			ps = conn.prepareCall("{call login(?,?)}");

			ps.setString(1, accountNumber);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result"); // login thành công hay k nó nằm ở đây
			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
			return -3;
		}
	}

	public void GetCustomerInfo() {
		try {

			ps = conn.prepareCall("{call getCustomerInfo()}");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				System.out.println(rs.getInt("Account_id") + "|" + rs.getString("AccountNumber") + " | "
						+ rs.getString("AccountName") + "|" + rs.getString("Phone"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int ChangePassword(int id, String password) {
		try {

			ps = conn.prepareCall("{call changePassword(?,?)}");

			ps.setInt(1, id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result");
			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	public int getID(String accountNumber) {
		try {

			ps = conn.prepareCall("{call getID(?)}");

			ps.setString(1, accountNumber);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result");
			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	public void getInfoByID(int ID) {
		try {

			ps = conn.prepareCall("{call getInfoByID(?)}");
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu

				System.out.println("ID tài khoản: " + rs.getString("AccountNumber"));
				System.out.println("Tên chủ tài khoản: " + rs.getString("AccountName"));
				System.out.println("Số điện thoại: " + rs.getString("Phone"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int checkAccountNumber(String accountNumber) {
		try {

			ps = conn.prepareCall("{call checkAccountNumber(?)}");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result");

			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	public String getNameToACTN(String accountNumber) { // ra name theo account number phục vụ việc login ban đầu
		try {

			ps = conn.prepareCall("{call getNameToACTN(?)}");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getString("AccountName");

			}
			return "";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";

		}
	}

	public int lockAccount(String accountNumber) {
		try {

			ps = conn.prepareCall("{call lockAccount(?)}");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result");

			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	public int checkActive(String accountNumber) {
		try {

			ps = conn.prepareCall("{call checkActive(?)}");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getInt("result");

			}
			return -2;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}

	public String checkPassfromID(int ID) {
		try {

			ps = conn.prepareCall("{call checkPassfromID(?)}");
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// nếu có dữ liệu
				return rs.getString("result");

			}
			return "";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
