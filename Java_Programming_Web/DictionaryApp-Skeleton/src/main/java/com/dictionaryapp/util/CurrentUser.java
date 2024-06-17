package com.dictionaryapp.util;

import com.dictionaryapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;


    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }
}
