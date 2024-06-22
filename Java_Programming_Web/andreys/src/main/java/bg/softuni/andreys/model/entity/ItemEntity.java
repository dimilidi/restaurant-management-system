package bg.softuni.andreys.model.entity;


import bg.softuni.andreys.model.enums.GenderEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;

    @ManyToOne
    private CategoryEntity category;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public ItemEntity() {
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
}
