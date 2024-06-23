package bg.softuni.exam.model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<PaintingEntity> paintings;

    @ManyToMany
    private List<PaintingEntity> favoritePaintings;

    @ManyToMany
    private List<PaintingEntity> ratedPaintings;

    public UserEntity() {
        this.paintings = new ArrayList<>();
        this.favoritePaintings = new ArrayList<>();
        this.ratedPaintings = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PaintingEntity> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<PaintingEntity> paintings) {
        this.paintings = paintings;
    }

    public List<PaintingEntity> getFavoritePaintings() {
        return favoritePaintings;
    }

    public void setFavoritePaintings(List<PaintingEntity> favoritePaintings) {
        this.favoritePaintings = favoritePaintings;
    }

    public List<PaintingEntity> getRatedPaintings() {
        return ratedPaintings;
    }

    public void setRatedPaintings(List<PaintingEntity> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
    }
}
