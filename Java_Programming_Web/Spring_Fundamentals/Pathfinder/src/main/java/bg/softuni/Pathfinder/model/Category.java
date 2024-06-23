package bg.softuni.Pathfinder.model;

import bg.softuni.Pathfinder.model.enums.CategoryEnumType;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private CategoryEnumType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryEnumType getName() {
        return name;
    }

    public void setName(CategoryEnumType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
