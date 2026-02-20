package com.auth;

import com.auth.others.Others;
import com.auth.service.AuthServer;

public class App {
    public static void main(String[] args) {
        try {
            Others.testDBConn();
            AuthServer as = new AuthServer();
            as.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
