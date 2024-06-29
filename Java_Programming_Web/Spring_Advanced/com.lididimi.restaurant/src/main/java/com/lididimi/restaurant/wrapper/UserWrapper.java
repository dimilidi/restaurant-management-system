package com.lididimi.restaurant.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserWrapper {

    private Long id;

    private String name;

    private String email;

    private String contactNumber;

    private String status;

    public UserWrapper() {}

    public UserWrapper(Long id, String name, String email, String contactNumber, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
    }
}
