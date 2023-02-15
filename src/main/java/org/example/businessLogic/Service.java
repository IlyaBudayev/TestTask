package org.example.businessLogic;

import org.example.SocketClient;
import org.java_websocket.client.WebSocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class Service {
    public static void appStart(String symbol, int n) throws URISyntaxException {
        final URI wss = new URI("wss://stream.binance.com:443/ws/" + symbol.toLowerCase() + "@depth");
        //override websocket onMessage method in order to get Updates from URL but not from wss
        WebSocketClient webSocketClient = new SocketClient(wss) {
            @Override
            public void onMessage(String s) {
                try {
                    System.out.println("got: " + getMessageFromUrl(symbol, n));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        webSocketClient.connect();
        webSocketClient.close();


    }

    static String getMessageFromUrl(String symbol, int n) throws IOException {
        final String limit = "&limit=" + n;
        final URL url = new URL("https://api.binance.com/api/v3/depth?symbol=" + symbol + limit);

        URLConnection urlConnection = url.openConnection();
        String inputLine;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            if ((inputLine = in.readLine()) != null) {
                return inputLine;
            }
        }
        return "nothing is there";
    }
}
