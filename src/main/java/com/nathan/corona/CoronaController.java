package com.nathan.corona;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CoronaController {

    @Autowired
    private ServiceCorona serviceCorona;


    @GetMapping(value = "/api/corona")
    public ResponseEntity<InsideText> getCoronaDetails(@RequestBody RequestBodyMapping requestBodyMapping) throws JsonProcessingException {
        System.out.println(requestBodyMapping.getResponse_url());
        serviceCorona.executeAsynchronously(requestBodyMapping.getResponse_url());
        return ResponseEntity.ok(new InsideText("mrkdwn", "Loading! Please wait..."));
    }
}
