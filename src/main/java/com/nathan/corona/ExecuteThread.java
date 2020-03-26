package com.nathan.corona;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExecuteThread implements Runnable{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    private String reqUrl;
    private String channelId;
    public ExecuteThread(){
    }

    @Override
    public void run() {
        List<InsideBlock> insideBlockList = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.getForEntity("https://lab.isaaclin.cn/nCoV/api/area", String.class);
        InsideBlock insideBlockHeader = new InsideBlock();
        insideBlockHeader.setType("section");
        insideBlockHeader.setText(new InsideText("mrkdwn", "*Country* \t\t *Total Cases* \t\t *Total Deaths* \t\t *Total Recovered*"));
        insideBlockList.add(insideBlockHeader);
        JsonNode root = null;
        try {
            root = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode result = root.path("results");
        String json = result.toPrettyString();
        List<ResponseObject> responseObjects = null;
        try {
            responseObjects = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, ResponseObject.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (ResponseObject responseObject : responseObjects) {
            if (responseObject.getCountryEnglishName().equals("Vietnam")) {
                InsideBlock insideBlock = new InsideBlock();
                insideBlock.setType("section");
                insideBlock.setText(convertObjectToInsideText(responseObject));
                insideBlockList.add(insideBlock);
                break;
            }
        }

        List<ResponseObject> sortedObject = responseObjects.stream().sorted(Comparator.comparing(ResponseObject::getDeadCount).reversed()).collect(Collectors.toList());
        int i = 0;
        for (ResponseObject responseObject : sortedObject){
            if (!responseObject.getCountryEnglishName().equals("Vietnam")){
                InsideBlock insideBlock = new InsideBlock();
                insideBlock.setType("section");
                insideBlock.setText(convertObjectToInsideText(responseObject));
                insideBlockList.add(insideBlock);
                i++;
            }
            if(i >= 10) break;
        }
        ResponseSlack responseSlack = new ResponseSlack();
        responseSlack.setChannel(this.channelId);
        responseSlack.setResponse_type("ephemeral");
        responseSlack.setBlocks(insideBlockList);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ResponseSlack> req = new HttpEntity<>(responseSlack, header);
        ResponseEntity responseEntity = restTemplate.postForObject(this.reqUrl, req, ResponseEntity.class);
        System.out.println(responseEntity);
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    private InsideText convertObjectToInsideText(ResponseObject responseObject)
    {
        InsideText insideText = new InsideText();
        insideText.setType("mrkdwn");
        String country = responseObject.getCountryEnglishName();
        long total = responseObject.getConfirmedCount();
        long death = responseObject.getDeadCount();
        long cured = responseObject.getCuredCount();
        String s = country + "\t\t\t\t" + total + "\t\t\t\t" + death + "\t\t\t\t" + cured;
        insideText.setText(s);
        return insideText;
    }
}
