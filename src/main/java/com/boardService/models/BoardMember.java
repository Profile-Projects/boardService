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

@Table(name = "board_member")
@Entity
@Getter
@Setter
public class BoardMember {

    @Id
    @Column
    @JsonProperty("sid")
    private String sid;

    @Column(name = "user_sid")
    @NonNull
    @JsonProperty("user_sid")
    private String userSid;

    @Column(name = "board_sid")
    @NonNull
    @JsonProperty("board_sid")
    private String boardSid;

    @Column(name = "props")
    @NonNull
    @JsonProperty("props")
    @JdbcTypeCode(SqlTypes.JSON)
    private BoardMember.BoardMemberProps boardMemberProps;

    public static BoardMember from(final BoardMember boardMember) {
        final BoardMember newBoardMember = new BoardMember();
        newBoardMember.setUserSid(boardMember.getUserSid());
        newBoardMember.setBoardSid(boardMember.getBoardSid());
        newBoardMember.setBoardMemberProps(boardMember.getBoardMemberProps());
        if (boardMember.getSid() != null) {
            newBoardMember.setSid(boardMember.getSid());
        }
        return newBoardMember;
    }

    private static class BoardMemberProps {

        @JsonProperty("permissions")
        @NonNull
        private BoardMemberPermissions permissions;
    }

    private enum BoardMemberPermissions {
        WRITE("WRITE"),
        REVIEW("REVIEW"),
        VIEW("VIEW");

        public final String label;

        private BoardMemberPermissions(final String label) {
            this.label = label;
        }
    }

//    private class PropsType implements UserType<Props> {
//
//        @Override
//        public int getSqlType() {
//            return Types.VARCHAR;
//        }
//
//        @Override
//        public Class returnedClass() {
//            return Props.class;
//        }
//
////        @Override
////        public Props replace(final Props props, )
//    }
}


