package bg.softuni.paintingcollectors.model.dto.painting;

import bg.softuni.paintingcollectors.model.entity.PaintingEntity;

public class OtherPaintingDTO {
    private Long id;
    private String name;
    private String author;
    private String style;
    private String addedBy;
    private String image;

    public OtherPaintingDTO() {
    }

    public OtherPaintingDTO(PaintingEntity painting) {
        id = painting.getId();
        name = painting.getName();
        author = painting.getAuthor();
        addedBy = painting.getOwner().getUsername();
        style = painting.getStyle().getName().name();
        image = painting.getImage();
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
