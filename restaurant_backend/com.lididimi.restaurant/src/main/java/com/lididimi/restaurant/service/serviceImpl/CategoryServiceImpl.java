package com.lididimi.restaurant.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lididimi.restaurant.constants.RestaurantConstants;
import com.lididimi.restaurant.exception.common.ObjectNotFoundException;
import com.lididimi.restaurant.exception.common.SomethingWentWrongException;
import com.lididimi.restaurant.exception.common.UnauthorizedAccessException;
import com.lididimi.restaurant.exception.user.AlreadyExistsException;
import com.lididimi.restaurant.jwt.JwtFilter;
import com.lididimi.restaurant.model.dto.CategoryDTO;
import com.lididimi.restaurant.model.entity.ProductEntity;
import com.lididimi.restaurant.repository.ProductRepository;
import com.lididimi.restaurant.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final JwtFilter jwtFilter;
    private final ModelMapper modelMapper;
    private final RestClient categoryRestClient;
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;


    public CategoryServiceImpl(
            JwtFilter jwtFilter,
            ModelMapper modelMapper,
            @Qualifier("categoriesRestClient") RestClient categoryRestClient,
            ObjectMapper objectMapper,
            ProductRepository productRepository
    ) {
        this.jwtFilter = jwtFilter;
        this.modelMapper = modelMapper;
        this.categoryRestClient = categoryRestClient;
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }


    @Override
    public List<CategoryDTO> getAllCategories(String filterValue) {
        log.info("Get all categories");

        return categoryRestClient
                .get()
                .uri("/categories")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public List<CategoryDTO> getCategoriesWithActiveProducts() {
        log.info("Get categories with active products");

        return productRepository.findAllActiveProducts().stream()
                .map(ProductEntity::getCategoryId)
                .distinct()
                .map(this::getCategoryById)
                .collect(Collectors.toList());
    }

    @Override
    public Long getCategoriesCount() {
        log.info("Get categories count");

        return categoryRestClient
                .get()
                .uri("/categories/count")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        log.info("Get category by id {} ", id);

        return categoryRestClient
                .get()
                .uri("/categories/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(CategoryDTO.class);
    }

    @Override
    public String addNewCategory(CategoryDTO categoryDTO) {
        log.info("Add new category");

        checkAdminAuthorization("Add new category");
        return handleAddNewCategory(categoryDTO);
    }

    @Override
    public String updateCategory(CategoryDTO categoryDTO) {
        log.info("Inside Update category {}", categoryDTO);

        checkAdminAuthorization("Update category");
        return handleCategoryUpdate(categoryDTO);
    }

    @Override
    public String deleteCategory(Long id) {
        log.info("Delete category with ID {}", id);

        checkAdminAuthorization("Delete category");
        return handleCategoryDeletion(id);
    }

    private void checkAdminAuthorization(String name) {
        if (!jwtFilter.isAdmin()) {
            log.info("Unauthorized access in " + name + "category - isAdmin {}", jwtFilter.isAdmin());
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }
    }

    private String handleAddNewCategory(CategoryDTO categoryDTO) {
        return categoryRestClient
                .post()
                .uri("/categories/add")
                .body(categoryDTO)
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new ObjectNotFoundException(RestaurantConstants.CATEGORY_NOT_FOUND);
                })
                .onStatus(status -> status.value() == 401, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
                })
                .onStatus(status -> status.value() == 400, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new AlreadyExistsException(RestaurantConstants.ALREADY_EXISTS);
                })
                .onStatus(status -> status.value() == 500, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new SomethingWentWrongException(RestaurantConstants.SOMETHING_WENT_WRONG);
                })
                .body(new ParameterizedTypeReference<>() {});
    }

    private String handleCategoryUpdate(CategoryDTO categoryDTO) {
        return  categoryRestClient
                .post()
                .uri("/categories/update")
                .body(categoryDTO)
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new ObjectNotFoundException(RestaurantConstants.CATEGORY_NOT_FOUND);
                })
                .onStatus(status -> status.value() == 401, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
                })
                .onStatus(status -> status.value() == 400, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new AlreadyExistsException(RestaurantConstants.ALREADY_EXISTS);
                })
                .onStatus(status -> status.value() == 500, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new SomethingWentWrongException(RestaurantConstants.SOMETHING_WENT_WRONG);
                })
                .body(new ParameterizedTypeReference<>() {});
    }

    private String handleCategoryDeletion(Long id) {
        deleteProductsByCategory(id);
         return categoryRestClient
                .delete()
                .uri("/categories/delete/{id}", id)
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new ObjectNotFoundException(RestaurantConstants.CATEGORY_NOT_FOUND);
                })
                .onStatus(status -> status.value() == 401, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
                })
                .onStatus(status -> status.value() == 500, (request, response) -> {
                    log.error("Error while updating category {}", response.getStatusCode());
                    throw new SomethingWentWrongException(RestaurantConstants.SOMETHING_WENT_WRONG);
                })
                .body(new ParameterizedTypeReference<>() {});
    }

    private void deleteProductsByCategory(Long id) {
        productRepository.getProductByCategory(id)
                .ifPresent(productRepository::deleteAll);
    }
}
