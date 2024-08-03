package com.lididimi.restaurant.service;

import com.lididimi.restaurant.model.dto.bestseller.BestSellerDTO;
import com.lididimi.restaurant.model.dto.guest.GuestDTO;
import com.lididimi.restaurant.model.dto.topEmployee.TopEmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface ReportService {

    List<BestSellerDTO> findBestSellers();

    List<TopEmployeeDTO> getTopEmployees();

    List<GuestDTO> findRegularGuestsWithFavoriteProducts();


}
