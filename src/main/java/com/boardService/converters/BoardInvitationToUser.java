package com.boardService.converters;

import com.boardService.models.BoardInvitation;
import com.boardService.models.Users;

public class BoardInvitationToUser {

    public static Users convert(final BoardInvitation boardInvitation, String name) {
        final Users newUser = new Users();
        newUser.setEmail(boardInvitation.getEmail());
        newUser.setName(name);
        return newUser;
    }
}
