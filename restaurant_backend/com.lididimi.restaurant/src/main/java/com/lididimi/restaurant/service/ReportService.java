package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.BestSellerDTO;
import com.lididimi.restaurant.model.dto.GuestDTO;
import com.lididimi.restaurant.model.dto.TopEmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface ReportService {

    List<BestSellerDTO> findBestSellers();

    List<TopEmployeeDTO> getTopEmployees();

    List<GuestDTO> findRegularGuestsWithFavoriteProducts();


}
