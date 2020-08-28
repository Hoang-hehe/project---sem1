package com.test.app.gui;

import com.test.app.bl.General;

public class Invoice extends General {

    public void print(){
        System.out.println("--------------------------------------");
        System.out.println("NGÂN HÀNG TMCP ĐẦU TƯ VÀ VAY VỐN VIỆT NAM (BHB)");
        System.out.println("--------------------------------------");
        System.out.println("Loại giao dịch: " );
        System.out.println("Số giao dịch: ");
        System.out.println("Thời gian giao dịch");
        System.out.println("Số tài khoản: ");
        System.out.println("Số tiền rút: ");
        System.out.println("Số dư tài khoản: ");
        System.out.println("/n");
        System.out.println("Phí: 1,000 VND | VAT: 100VND");
        System.out.println("Phí in hoá đơn: 500 VND");
        System.out.println("VAT in hoá đơn: 50 VND");

    }
    
    
}