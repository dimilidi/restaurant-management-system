package com.lididimi.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class MessageController {

    private final ObjectMapper objectMapper;

    public MessageController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/messages")
    public Map<String, Object> getMessages(@RequestParam(name = "lang", defaultValue = "en") String lang) throws IOException {
        String fileName = "i18n/messages_" + lang + ".json";
        return objectMapper.readValue(new ClassPathResource(fileName).getInputStream(), Map.class);
    }
}
