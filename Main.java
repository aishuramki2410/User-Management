package com.nw.ezpay.user.app;

import com.nw.ezpay.user.controller.UserController;

public class Main {
    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.start();
    }
}
