package com.lididimi.restaurant.com.lididimi.restaurant.service.serviceImpl;

import com.lididimi.restaurant.com.lididimi.restaurant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<String> register(Map<String, String> requestMap) {
        return null;
    }
}
