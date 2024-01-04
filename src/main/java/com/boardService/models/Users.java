package com.boardService.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Users {

    @Id
    @Column
    @Nullable
    private String sid;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "email")
    @NonNull
    private String email;

    public static Users from(final Users u) {
        final Users newUser = new Users();
        newUser.setName(u.getName());
        newUser.setEmail(u.getEmail());
        if (u.getSid() != null) {
            u.setSid(u.getSid());
        }
        return newUser;
    }
}
