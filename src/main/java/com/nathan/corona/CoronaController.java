package com.nathan.corona;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CoronaController {

    @Autowired
    private ServiceCorona serviceCorona;


    @GetMapping(value = "/api/corona")
    public ResponseEntity<ResponsePleaseWait> getCoronaDetails(@RequestParam(name = "response_url") String responseUrl
    , @RequestParam(name = "channel_id") String channelId) throws JsonProcessingException {
        System.out.println(responseUrl);
        serviceCorona.executeAsynchronously(responseUrl, channelId);
        return ResponseEntity.ok(new ResponsePleaseWait("in_channel", "mrkdwn", "Loading! Please wait..."));
    }
}
