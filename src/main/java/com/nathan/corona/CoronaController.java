package com.nathan.corona;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CoronaController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @RequestMapping(value = "/api/corona")
    public ResponseEntity<ResponseSlack> getCoronaDetails() throws JsonProcessingException {
        List<InsideBlock> insideBlockList = new ArrayList<>();
        ResponseEntity<String> response = restTemplate.getForEntity("https://lab.isaaclin.cn/nCoV/api/area", String.class);
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode result = root.path("results");
        String json = result.toPrettyString();
        List<ResponseObject> responseObjects = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, ResponseObject.class));
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
        responseSlack.setChannel("corona");
        responseSlack.setResponse_type("in_channel");
        responseSlack.setBlocks(insideBlockList);
        return ResponseEntity.ok(responseSlack);
    }

    private InsideText convertObjectToInsideText(ResponseObject responseObject)
    {
        InsideText insideText = new InsideText();
        insideText.setType("mrkdwn");
        String country = responseObject.getCountryEnglishName();
        long total = responseObject.getConfirmedCount();
        long death = responseObject.getDeadCount();
        long cured = responseObject.getCuredCount();
        String s = country + "\t" + total + "\t" + death + "\t" + cured;
        insideText.setText(s);
        return insideText;
    }
}
