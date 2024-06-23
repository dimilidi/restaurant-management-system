package bg.softuni.andreys.service.impl;

import bg.softuni.andreys.model.dto.ItemAddDTO;
import bg.softuni.andreys.model.dto.ItemViewDTO;
import bg.softuni.andreys.model.entity.CategoryEntity;
import bg.softuni.andreys.model.entity.ItemEntity;
import bg.softuni.andreys.repository.CategoryRepository;
import bg.softuni.andreys.repository.ItemRepository;
import bg.softuni.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {
    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemServiceImpl(ModelMapper modelMapper, ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void add(ItemAddDTO data) {

        ItemEntity itemToAdd = modelMapper.map(data, ItemEntity.class);
        CategoryEntity category = categoryRepository.findByName(data.getCategory());
        itemToAdd.setCategory(category);

        itemRepository.save(itemToAdd);
    }

    @Override
    public List<ItemViewDTO> findAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> {
                    ItemViewDTO itemViewDTO = modelMapper.map(item, ItemViewDTO.class);
                    itemViewDTO.setImageUrl(String.format("/img/%s-%s.jpg", item.getGender().name(), item.getCategory().getName().name()));

                    return itemViewDTO;
                })
                .toList();
    }

    @Override
    public ItemViewDTO findById(Long id) {
         return itemRepository.findById(id)
                 .map(item -> {
                     ItemViewDTO itemViewDTO = modelMapper.map(item, ItemViewDTO.class);
                     itemViewDTO.setImageUrl(String.format("/img/%s-%s.jpg", item.getGender().name(), item.getCategory().getName().name()));

                     return itemViewDTO;
                 }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        this.itemRepository.deleteById(id);
    }

}
