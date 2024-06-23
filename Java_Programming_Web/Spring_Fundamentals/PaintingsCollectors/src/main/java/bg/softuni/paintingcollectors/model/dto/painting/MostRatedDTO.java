package bg.softuni.paintingcollectors.model.dto.painting;

import bg.softuni.paintingcollectors.model.entity.PaintingEntity;

public class MostRatedDTO {
    private String name;
    private String author;
    private long votes;

    public MostRatedDTO() {
    }

    public MostRatedDTO(PaintingEntity topRatedPainting) {
        this.name = topRatedPainting.getName();
        this.author = topRatedPainting.getAuthor();
        this.votes = topRatedPainting.getVotes();
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

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
