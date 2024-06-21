package bg.softuni.andreys.model.entity;

import bg.softuni.andreys.model.enums.CategotyNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CategotyNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryEntity() {
    }

    public CategoryEntity(CategotyNameEnum name, String description) {
        this();

        this.name = name;
        this.description = description;
    }

    public CategotyNameEnum getName() {
        return name;
    }

    public void setName(CategotyNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
