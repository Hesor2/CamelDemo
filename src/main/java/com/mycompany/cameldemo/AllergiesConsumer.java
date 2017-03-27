/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cameldemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class AllergiesConsumer extends ScheduledPollConsumer
{
    private AllergiesEndpoint endpoint;

    public AllergiesConsumer(AllergiesEndpoint endpoint, Processor processor) 
    {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.setDelay(endpoint.getConfiguration().getDelay());
    }

    @Override
    protected int poll() throws Exception 
    {

        System.out.println("new poll");
        String operationPath = endpoint.getOperationPath();

        if (operationPath.equals("search/feeds")) return processSearchFeeds();

        // only one operation implemented for now !
        throw new IllegalArgumentException("Incorrect operation: " + operationPath);
    }

    private JsonObject performGetRequest() throws ClientProtocolException, IOException 
    {
        HttpClient client = HttpClientBuilder.create().build();
        //HttpGet request = new HttpGet("http://cloud.feedly.com/v3/" + uri);
        HttpGet request = new HttpGet("http://80.255.6.114:4567/allergies");
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) throw new RuntimeException("Feedly API returned wrong code: " + statusCode);

        JsonParser parser = new JsonParser();
        InputStreamReader sr = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
        BufferedReader br = new BufferedReader(sr);
        JsonObject json = (JsonObject) parser.parse(br);
        br.close();
        sr.close();

        return json;
    }

    private int processSearchFeeds() throws Exception 
    {
        String query = endpoint.getConfiguration().getQuery();
        JsonObject json = performGetRequest();

        JsonArray feeds = (JsonArray) json.get("allergies");
        List feedList = new ArrayList();
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        
        for (JsonElement f : feeds) 
        {
            Feed feed = gson.fromJson(f, Feed.class);
            feedList.add(feed);
        }
        
        Exchange exchange = getEndpoint().createExchange();
        exchange.getIn().setBody(feedList, ArrayList.class);
        getProcessor().process(exchange);

        return 1;
    }
}
