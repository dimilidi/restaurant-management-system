package com.lididimi.restaurant.model.user;

import com.lididimi.restaurant.model.enums.StatusNameEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class RestaurantUserDetails extends User {

    private String email;
    private String name;
    private StatusNameEnum status;
    private List<String> roles;

    public RestaurantUserDetails(
            String email,
            String name,
            String password,
            StatusNameEnum status,
            Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.name = name;
        this.status = status;
        this.roles = authorities.stream().map(GrantedAuthority::getAuthority).toList();
    }
}


