package com.qa.client;


import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    // 1. GET Method without header
    public CloseableHttpResponse get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);     // http GET request
        return httpClient.execute(httpGet);
    }

    // 2. GET Method with header
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        return httpClient.execute(httpGet);
    }

    public int getResponseStatusCode(CloseableHttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode();
    }

    public JSONObject getJsonResponseString(CloseableHttpResponse httpResponse) throws IOException {
        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        return new JSONObject(responseString);
    }

    public HashMap<String, String> getAllHeadersHashMap(CloseableHttpResponse httpResponse) {
        Header[] headersAray = httpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header : headersAray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        return allHeaders;
    }

    // 3. POST Method
    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(entityString)); // Payload
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        return httpClient.execute(httpPost);
    }

    //4. PUT Method
    public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(entityString));
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpPut.addHeader(entry.getKey(), entry.getValue());
        }
        return httpClient.execute(httpPut);
    }

    //5. DELETE Method
    public CloseableHttpResponse delete(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        return httpClient.execute(httpDelete);
    }
}
