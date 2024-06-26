package com.lididimi.restaurant.com.lididimi.restaurant.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<String> register(Map<String, String> requestMap);
}
