package bg.softuni.shoppinglist.model.entity;

import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    CategoryNameEnum name;

    @Column(columnDefinition = "TEXT")
    String description;

    public CategoryEntity() {
    }
  public CategoryEntity(CategoryNameEnum name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
