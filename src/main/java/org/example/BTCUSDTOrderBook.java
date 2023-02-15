package org.example;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.example.Application.appStart;

public class BTCUSDTOrderBook {
    public static void main(String[] args) throws IOException, URISyntaxException {
        //Run application
        appStart("BTCUSDT", 500);
    }
}