package bg.softuni.exam.model.entity;

import bg.softuni.exam.model.enums.StyleNameEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "styles")
public class StyleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleNameEnum name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public StyleEntity() {
    }

    public StyleEntity(StyleNameEnum name, String description) {
        this();

        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StyleNameEnum getName() {
        return name;
    }

    public void setName(StyleNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
