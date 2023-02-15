package org.example.businessLogic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.example.businessLogic.Service.getMessageFromUrl;


class ServiceTest {

    //HAPPY
    @Test
    void ensureThatNumberOfBidOrAskIsOk() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(getMessageFromUrl("BTCUSDT", 500));
        JSONArray bids = (JSONArray) json.get("bids");
        Assertions.assertEquals(500, bids.size());
    }

    //UNHAPPY
    @Test
    void ensureThatNumberOfBidOrAskIsNOTOk() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(getMessageFromUrl("BTCUSDT", 100));
        JSONArray asks = (JSONArray) json.get("asks");
        Assertions.assertNotEquals(21, asks.size());
    }
}