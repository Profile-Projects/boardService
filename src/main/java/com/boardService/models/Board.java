package com.boardService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "board")
public class Board{

    @Id
    @Column(name = "sid")
    @Nullable
    private String sid;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    @NonNull
    private String description;

    @Column(name = "owner_sid")
    @JsonProperty("owner_sid")
    @NonNull
    private String ownerSid;

    @Column(name = "link")
    @Nullable
    private String link;

    public static Board from(final Board t) {
        final Board newBoard = new Board();
        newBoard.setName(t.getName());
        newBoard.setDescription(t.getDescription());
        newBoard.setLink(t.getLink());
        newBoard.setOwnerSid(t.getOwnerSid());
        if (t.getSid() != null) {
            newBoard.setSid(t.getSid());
        }
        return newBoard;
    }
}
