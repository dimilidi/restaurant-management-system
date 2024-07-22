package com.lididimi.restaurant.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface ReportService {

    List<Map<String, Object>> findBestSellers();

    List<Map<String, Object>> getTopEmployees();

    List<Map<String, Object>> findRegularGuestsWithFavoriteProducts();


}
