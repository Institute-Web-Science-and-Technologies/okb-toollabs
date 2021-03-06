/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.authentication;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import static java.util.logging.Logger.global;
import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Mujji
 */
public class auth {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        // TODO code application logic here
        
      HttpClient httpclient = HttpClients.createDefault();
    HttpPost httppost = new HttpPost("https://en.wikipedia.org/w/api.php?action=query&meta=tokens");

    // Request parameters and other properties.
    List<NameValuePair> params = new ArrayList<NameValuePair>(6);
    params.add(new BasicNameValuePair("type", "login"));
    params.add(new BasicNameValuePair("format", "json"));

    
    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

    //Execute and get the response.
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity entity = response.getEntity();
    String token = "";

    if (entity != null) {
        InputStream instream = entity.getContent();
        try {
            // do something useful
           String responseString = EntityUtils.toString(entity, "UTF-8");
           //System.out.println(responseString);
           ObjectMapper mapper = new ObjectMapper();
           JsonFactory factory = mapper.getJsonFactory(); // since 2.1 use mapper.getFactory() instead
           JsonParser jp = factory.createJsonParser(responseString);
           JsonNode actualObj = mapper.readTree(jp);
           if (actualObj.get("query")!=null){
                 token = actualObj.get("query").get("tokens").get("logintoken").asText();
                 clientLogin(token);
           }
        } finally {
            instream.close();
        }
        System.out.println(token);
    }
    }
    
    
    public static String clientLogin (String token) throws IOException{
    HttpClient httpclient = HttpClients.createDefault();
    HttpPost httppost = new HttpPost("https://en.wikipedia.org/w/api.php");

    // Request parameters and other properties.
    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
    params.add(new BasicNameValuePair("action", "clientlogin"));
    params.add(new BasicNameValuePair("loginreturnurl", "http://example.com"));
    params.add(new BasicNameValuePair("username", "user"));
    params.add(new BasicNameValuePair("password", "secret"));
    params.add(new BasicNameValuePair("rememberMe", "1"));
    params.add(new BasicNameValuePair("logintoken", token));
    params.add(new BasicNameValuePair("format", "json"));

    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

    //Execute and get the response.
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity entity = response.getEntity();

    if (entity != null) {
        InputStream instream = entity.getContent();
        try {
            // do something useful
           String responseString = EntityUtils.toString(entity, "UTF-8");
           System.out.println(responseString);
            return responseString;
        } finally {
            instream.close();
        }
    }
    return null;
    }
}
