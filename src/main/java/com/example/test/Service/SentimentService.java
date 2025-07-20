package com.example.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class SentimentService {

    @Autowired
    private RestTemplate restTemplate;

    private final String PYTHON_API_URL = "http://127.0.0.1:8000/analyze";

    public String getSentiment(String reviewText) {

        Map<String, String> requestBody = Collections.singletonMap("text", reviewText);

        try {

            Map<String, Object> response = restTemplate.postForObject(PYTHON_API_URL, requestBody, Map.class);

            if (response != null && response.containsKey("sentiment")) {
                return (String) response.get("sentiment");
            } else {
                return "Error: Invalid response from API";
            }
        } catch (RestClientException e) {
            System.err.println("Error calling sentiment API: " + e.getMessage());
            return "Error: Could not connect to sentiment service";
        }
    }
}