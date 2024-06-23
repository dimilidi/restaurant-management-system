package bg.softuni.shoppinglist.model.dto;

import java.util.UUID;

public class ProductViewDTO {

    private UUID id;
    private String name;
    private String price;

    public ProductViewDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
