package bg.softuni.paintingcollectors.util;

import bg.softuni.paintingcollectors.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;


    public void login(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }

    public boolean isLoggedIn() {
        return id != null;
    }

    public Long id() {
        return id;
    }


    public String username() {
        return username;
    }


}
