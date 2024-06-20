package com.bonappetit.service;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bonappetit.model.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser, RecipeRepository recipeRepository, CategoryService categoryService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    public boolean register(UserRegisterDTO data) {
        Optional<UserEntity> existingUser = userRepository
                .findByUsernameOrEmail(data.getUsername(), data.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(data.getUsername());
        userEntity.setEmail(data.getEmail());
        userEntity.setPassword(passwordEncoder.encode(data.getPassword()));

        userRepository.save(userEntity);
        return true;
    }

    public boolean login(UserLoginDTO data) {
        return userRepository.findByUsername(data.getUsername())
                .filter(user -> passwordEncoder.matches(data.getPassword(), user.getPassword()))
                .map(user -> {
                    currentUser.login(user);
                    return true;
                })
                .orElse(false);
    }

    public boolean isLoggedIn() {
        return this.currentUser.id() != null;
    }

    public UserEntity findByUsername(String username) {

        return this.userRepository
                .findByUsername(username)
                .get();
    }

    public void logout() {
        currentUser.logout();
    }

    @Transactional
    public List<RecipeEntity> findFavourites(Long userId) {
        return userRepository.findById(userId)
                .map(UserEntity::getFavouriteRecipes)
                .orElseGet(ArrayList::new);
    }

}
