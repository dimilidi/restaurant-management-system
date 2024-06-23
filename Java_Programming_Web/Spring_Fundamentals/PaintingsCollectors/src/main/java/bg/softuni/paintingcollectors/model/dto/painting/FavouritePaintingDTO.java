package bg.softuni.paintingcollectors.model.dto.painting;

import bg.softuni.paintingcollectors.model.entity.PaintingEntity;

public class FavouritePaintingDTO  {

    private Long id;
    private String name;
    private String author;
    private String owner;
    private String image;

    public FavouritePaintingDTO() {
    }

    public FavouritePaintingDTO(PaintingEntity painting) {
        this.id = painting.getId();
        this.name = painting.getName();
        this.author = painting.getAuthor();
        this.owner = painting.getOwner().getUsername();
        this.image = painting.getImage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
