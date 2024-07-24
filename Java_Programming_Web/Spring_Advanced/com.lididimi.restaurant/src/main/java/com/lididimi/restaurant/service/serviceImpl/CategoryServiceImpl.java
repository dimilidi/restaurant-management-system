package com.lididimi.restaurant.service.serviceImpl;


import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final JwtFilter jwtFilter;
    private final ModelMapper modelMapper;
    private final RestClient categoryRestClient;
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;


    public CategoryServiceImpl(JwtFilter jwtFilter, ModelMapper modelMapper, @Qualifier("categoriesRestClient") RestClient categoryRestClient, ObjectMapper objectMapper, ProductRepository productRepository) {
        this.jwtFilter = jwtFilter;
        this.modelMapper = modelMapper;
        this.categoryRestClient = categoryRestClient;
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void addNewCategory(CategoryDTO categoryDTO) {
        log.info("Add new category");
        categoryRestClient
                .post()
                .uri("/categories")
                .body(categoryDTO)
                .retrieve();


      /*  if (!jwtFilter.isAdmin()) {
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }*/
   /*     categoryRepository.save(getCategoryFromMap(categoryDTO, false));
        return RestaurantConstants.CATEGORY_ADD_SUCCESS;*/
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

        List<ProductEntity> activeProducts = productRepository.findAllActiveProducts();
        List<Long> categoryIds = activeProducts.stream()
                .map(ProductEntity::getCategoryId)
                .collect(Collectors.toList());
        log.info("active category ids: {}", categoryIds);

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Long id : categoryIds) {
            CategoryDTO categoryById = getCategoryById(id);
            categoryDTOS.add(categoryById);
        }

        List<CategoryDTO> allCategories = getAllCategories("true");

     List<CategoryDTO> activeCategories = getAllCategories("false");
       // List<CategoryDTO> categoriesByIds = getCategoriesByIds(categoryIds);
        log.info("Get categories with active products");
        for (CategoryDTO c : categoryDTOS) {

            log.info("Category: {}", c);
        }

        return categoryDTOS;
    }


    @Override
    public Object updateCategory(CategoryDTO categoryDTO) {
        log.info("Inside Update new category {}", categoryDTO);

            return  categoryRestClient
                    .put()
                    .uri("/categories/filter")
                    .body(categoryDTO)
                    .retrieve()
                  //  .exchange((req, res) -> { log.info(res.getClass().getName()); return res;})

                  /*  .retrieve()
                    .onStatus(status -> status.value() == 404, (request, response) -> {
                        throw new ObjectNotFoundException(RestaurantConstants.CATEGORY_NOT_FOUND);
                    })
                    .onStatus(status -> status.value() == 500, (request, response) -> {
                        log.error("Error while updating category {}", categoryDTO);
                        throw new SomethingWentWrongException(RestaurantConstants.SOMETHING_WENT_WRONG);
                    })
                    .onStatus(status -> status.value() == 200, (request, response) -> {
                        SuccessResponse successResponse = objectMapper.readValue(response.getBody(), new TypeReference<>() {
                        });
                        log.info("BODY {}",successResponse );


                       // new SuccessResponse(response.getStatusCode().value(), RestaurantConstants.CATEGORY_UPDATE_SUCCESS, null);
                        log.info("Update category {}", categoryDTO);
                    })*/
                    ;



              /* .exchange((req, res) -> {
                   var result = "Result";
                   if (res.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
                       log.info( objectMapper.readValue(res.getBody(), new TypeReference<>() {}));
                     result =  objectMapper.readValue(res.getBody(), new TypeReference<>() {});
                   }
                   return result;
               });*/




     /*   if(!jwtFilter.isAdmin()) {
            log.info("Updating category - isAdmin {}", jwtFilter.isAdmin());
            throw new UnauthorizedAccessException(RestaurantConstants.UNAUTHORIZED_ACCESS);
        }*/



       /* Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryDTO.getId());
        if(categoryOptional.isPresent()) {
            categoryRepository.save(getCategoryFromMap(categoryDTO, true));
            return RestaurantConstants.CATEGORY_UPDATE_SUCCESS;
        } else {
            throw  new ObjectNotFoundException(RestaurantConstants.CATEGORY_NOT_FOUND);
        }*/


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
    public List<CategoryDTO> getCategoriesByIds(List<Long> categoryIds)  {
        log.info("Get Categories Set by ids {} ", categoryIds);

        return categoryRestClient
                .get()
                .uri("/categories")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }





  /*  private CategoryEntity getCategoryFromMap(CategoryDTO categoryDTO, Boolean isAdd) {
        CategoryEntity categoryEntity = new CategoryEntity();

        if (isAdd) {
            categoryEntity.setId(categoryDTO.getId());
        }
        categoryEntity.setName(categoryDTO.getName());
        return categoryEntity;
    }

    private CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }*/
}
