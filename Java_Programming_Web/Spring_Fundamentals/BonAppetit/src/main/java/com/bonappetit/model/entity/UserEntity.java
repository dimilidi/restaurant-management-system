package com.bonappetit.model.entity;


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

    @OneToMany(mappedBy = "addedBy")
    private List<RecipeEntity> addedRecipes;

    @ManyToMany
    private List<RecipeEntity> favouriteRecipes;

    public UserEntity() {
        this.addedRecipes = new ArrayList<>();
        this.favouriteRecipes = new ArrayList<>();
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

    public List<RecipeEntity> getAddedRecipes() {
        return addedRecipes;
    }

    public void setAddedRecipes(List<RecipeEntity> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    public List<RecipeEntity> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(List<RecipeEntity> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }


    public void addFavourite(RecipeEntity recipeEntity) {
        this.favouriteRecipes.add(recipeEntity);
    }
}
