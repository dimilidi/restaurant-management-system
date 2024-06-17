package bg.softuni.coffeeshop.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @Column(nullable = false)
    private Integer neededTime;

    public Category() {
    }

    public Category(CategoryNameEnum name) {
        this.name = name;
        switch(name){
            case COFFEE -> neededTime = 2;
            case DRINK -> neededTime = 1;
            case CAKE -> neededTime = 10;
            case OTHER -> neededTime = 5;
        }
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
