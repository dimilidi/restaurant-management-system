package bg.softuni.paintingcollectors.model.dto.painting;

import bg.softuni.paintingcollectors.model.enums.StyleNameEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class AddPaintingDTO {

    @NotBlank(message = "Name must not be empty!")
    @Size(min = 5, max = 40, message = "Name must be between 5 and 40 characters!")
    private String name;

    @NotBlank(message = "Author name must not be empty!")
    @Size(min = 5, max = 30, message = "Author name must be between 5 and 30 characters!")
    private String author;

    @NotBlank(message = "Url must not be empty!")
    @URL(message = "Please enter valid image URL!")
    private String image;

    @NotNull(message = "You must select a style!")
    private StyleNameEnum style;

    public AddPaintingDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public StyleNameEnum getStyle() {
        return style;
    }

    public void setStyle(StyleNameEnum style) {
        this.style = style;
    }
}


