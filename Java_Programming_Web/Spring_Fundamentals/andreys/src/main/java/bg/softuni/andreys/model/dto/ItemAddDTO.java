package bg.softuni.andreys.model.dto;

import bg.softuni.andreys.model.enums.CategotyNameEnum;
import bg.softuni.andreys.model.enums.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ItemAddDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name length must be more than two characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 3, message = "Description length must be more than two characters")
    private String description;

    @NotNull(message = "Category must be selected!")
    private CategotyNameEnum category;

    @Positive(message = "Price must be positive number")
    private BigDecimal price;

    @NotNull(message = "Gender must be selected!")
    private GenderEnum gender;

    public ItemAddDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public CategotyNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategotyNameEnum category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

}
