package org.example;

import java.net.URISyntaxException;

import static org.example.businessLogic.Service.appStart;

public class OrderBookApplication {
    public static void main(String[] args) throws URISyntaxException {
        //Run application
        appStart("BTCUSDT", 500);
    }
}