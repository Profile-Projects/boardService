package com.boardService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Setter
@Getter
@Table(name = "board_invitation")
public class BoardInvitation {

    @Id
    @Column(name = "sid")
    @NonNull
    private String sid;

    @Column(name = "email")
    @NonNull
    @JsonProperty("email")
    private String email;

    @Column(name = "board_sid")
    @NonNull
    @JsonProperty("board_sid")
    private String boardSid;

    @Column(name = "props")
    @JdbcTypeCode(SqlTypes.JSON)
    @JsonProperty("props")
    @NonNull
    private BoardInvitationProps props;

    @Column(name = "mark_complete")
    @JsonProperty("mark_complete")
    private Boolean markComplete;

    public static BoardInvitation from(final BoardInvitation boardInvitation) {
        final BoardInvitation newBoardInvitation = new BoardInvitation();
        newBoardInvitation.setEmail(boardInvitation.getEmail());
        newBoardInvitation.setBoardSid(boardInvitation.getBoardSid());
        newBoardInvitation.setProps(boardInvitation.getProps());
        newBoardInvitation.setMarkComplete(boardInvitation.getMarkComplete());
         if (boardInvitation.getSid() != null) {
            newBoardInvitation.setSid(boardInvitation.getSid());
        }
        return newBoardInvitation;
    }

    private static class BoardInvitationProps {

        @JsonProperty("permissions")
        @NonNull
        private BoardMemberPermission permissions;
    }
}
