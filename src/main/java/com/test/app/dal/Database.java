package com.test.app.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {

	private Connection conn; 
	private PreparedStatement  ps; 
	public Database() {
		try	{
			conn = DriverManager.getConnection(Common.connString);
			// System.out.print("Connect successfully \n");
		}catch(Exception ex){
			System.out.print("Connection error: "+ex.getMessage());
		}
	}
	

	public int trade(int userId,int amount,String trade_tye) {//hàm giao dịch
		try {
			
			ps = conn.prepareCall("{call trade(?,?,?)}");
			ps.setInt(1, userId);
			ps.setInt(2, amount);
			ps.setString(3,trade_tye);
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs.getInt("result");
			}
			return -1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}//call tới tên procedure trong csdl
	}
	
	//hàm tính số dư của tài khoản có id là userId
	public int getBalance(int userId) {
		try {
			
			ps = conn.prepareCall("{call getBalance(?)}");
			//truyền giá trị cho các tham sô
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs.getInt("balance");
			}
			return -1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}//call tới tên procedure trong csdl
	}
	
	//hàm lấy lịch sử giao dịch của 1 tài khoản nhất định
	public ResultSet GetHistoryTrade(int accId) {
		try {
			
			ps = conn.prepareCall("{call getHistoryTrade(?)}");
			//truyền giá trị cho các tham sô
			ps.setInt(1, accId);
			
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs;
			}
			return null;
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}//call tới tên procedure trong csdl
	}

	public int InsertAccount(String accountNumber,String password,String name,String phone,int isActive) {
		try {
			
			ps = conn.prepareCall("{call insertAccount(?,?,?,?,?)}");
			//truyền giá trị cho các tham sô
			ps.setString(1, accountNumber);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, phone);
			ps.setInt(5, isActive);
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs.getInt("result"); //insert thành công hay khong nó nằm ở đây
			}
			return -2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}//call tới tên procedure trong csdl
	}
	
	public int UpdateAccount(int id,String accountNumber,String password,String name,String phone,int isActive) {
		try {
			
			ps = conn.prepareCall("{call updateAccount(?,?,?,?,?,?)}");
			//truyền giá trị cho các tham sô
			ps.setInt(1, id);
			ps.setString(2, accountNumber);
			ps.setString(3, password);
			ps.setString(4, name);
			ps.setString(5, phone);
			ps.setInt(6, isActive);
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs.getInt("result"); //insert thành công hay khong nó nằm ở đây
			}
			return -2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}//call tới tên procedure trong csdl
	}
	
	public int login(String accountNumber,String password) {
		try {
			
			ps = conn.prepareCall("{call login(?,?)}");
			//return 333;
			//truyền giá trị cho các tham sô
			ps.setString(1, accountNumber);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs.getInt("result"); //login thành công hay k nó nằm ở đây
			}
			return -2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
			return -3;
		}//call tới tên procedure trong csdl
	}
	
	public ResultSet GetCustomerInfo() {
		try {
			
			ps = conn.prepareCall("{call getCustomerInfo()}");						
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs;
			}
			return null;
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}//call tới tên procedure trong csdl
	}
	
	public int ChangePassword(int id,String password) {
		try {
			
			ps = conn.prepareCall("{call changePassword(?,?)}");
			//truyền giá trị cho các tham sô
			ps.setInt(1, id);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();//thực thi câu lệnh
			while(rs.next()) {//nếu có dữ liệu
				return rs.getInt("result"); //login thành công hay k nó nằm ở đây
			}
			return -2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}//call tới tên procedure trong csdl
	}

}
