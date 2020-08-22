package com.test.app.gui;

import java.util.Scanner;

import com.test.app.dal.Database;
import com.test.app.persistance.Account;

public class login {

	private Account member;
	public Account test(){
		return member;
	}
    public Account run() {
        System.out.println("Chào mừng đến với Ngân Hàng đầu tư và phát triển Bốc Bát Họ Bank (BBH)");
		String choice;
		String ID;
		String password;
		int login;
		System.out.println("----------------------------------------------------");
		System.out.println("1. Login");
		System.out.println("2. Thoát");
		System.out.println("----------------------------------------------------");
		try {
			Database db = new Database();

			Scanner input = new Scanner(System.in);
			choice = input.nextLine();
			switch (choice) {
				case "1":
					System.out.println("Nhập ID: ");
					ID = input.nextLine();
					System.out.println("Nhập password");
					password = input.nextLine();
					login = db.login(ID, password);
					if (login == 1) {
						System.out.println("Đăng nhập thành công!");
						member.setAccount_number(ID);
						member.setPassword(password);
						member.toString();
						return member;
					} else {
						System.out.println("Lỗi!, tài khoản không tồn tại, hoặc sai mật khẩu...");
						break;
					}

					

				default:
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return member;
	}

	public Account getMember() {
		return member;
	}

	public void setMember(Account member) {
		this.member = member;
	}
	
        
}