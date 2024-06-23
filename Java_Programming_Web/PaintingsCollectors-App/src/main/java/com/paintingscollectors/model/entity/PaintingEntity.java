package bg.softuni.exam.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "paintings")
public class PaintingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private UserEntity author;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private StyleEntity style;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private UserEntity owner;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isFavorite;

    @Column(nullable = false)
    private long votes;

    public PaintingEntity() {
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

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public StyleEntity getStyle() {
        return style;
    }

    public void setStyle(StyleEntity style) {
        this.style = style;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
