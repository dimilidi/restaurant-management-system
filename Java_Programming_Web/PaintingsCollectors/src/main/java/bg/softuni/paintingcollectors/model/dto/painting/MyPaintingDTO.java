package bg.softuni.paintingcollectors.model.dto.painting;


import bg.softuni.paintingcollectors.model.entity.PaintingEntity;

public class MyPaintingDTO {

    private Long id;
    private String name;
    private String author;
    private String style;
    private String image;

    public MyPaintingDTO() {
    }

    public MyPaintingDTO(PaintingEntity painting) {
        this.id = painting.getId();
        this.name = painting.getName();
        this.author = painting.getAuthor();
        this.style = painting.getStyle().getName().name();
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
