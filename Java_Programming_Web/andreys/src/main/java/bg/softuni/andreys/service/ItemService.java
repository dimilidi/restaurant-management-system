package bg.softuni.andreys.service;

import bg.softuni.andreys.model.dto.ItemAddDTO;
import bg.softuni.andreys.model.dto.ItemViewDTO;

import java.util.List;

public interface ItemService {
    void add(ItemAddDTO data);

    List<ItemViewDTO> findAllItems();

    ItemViewDTO findById(Long id);

    void delete(Long id);
}
