package com.test.app.gui;

import java.util.Scanner;

public class mainMenu {

    public void run(login a) {
        String choice;
        System.out.println(a.run().getAccount_id());
        System.out.println(a.run().getPassword());
        System.out.println("Mời giao dịch");
        System.out.println("---------------------------------------");
        System.out.println("1. Kiểm tra số dư");
        System.out.println("2. Rút tiền ");
        System.out.println("3. Chuyển khoản");
        System.out.println("4. Đăng xuất");
        System.out.println("---------------------------------------");
        System.out.println("#chọn: ");
        Scanner input = new Scanner(System.in);
        // choice = input.nextLine();
        // switch (choice) {
        //     case 1:

        //         break;
        //     case 2:

        //         break;
        //     case 3:

        //         break;
        //     case 4:

        //         break;
        //     default:
        //         break;
        // }

    }

}